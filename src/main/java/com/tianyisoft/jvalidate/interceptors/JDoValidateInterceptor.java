package com.tianyisoft.jvalidate.interceptors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianyisoft.jvalidate.JValidator;
import com.tianyisoft.jvalidate.annotations.JValidated;
import com.tianyisoft.jvalidate.exceptions.ValidateFailedException;
import com.tianyisoft.jvalidate.utils.BindingErrors;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class JDoValidateInterceptor implements HandlerInterceptor {
    private final JdbcTemplate jdbcTemplate;

    public JDoValidateInterceptor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        String json = StreamUtils.copyToString(request.getInputStream(), Charset.forName(request.getCharacterEncoding()));
        AtomicReference<Boolean> jsonError = new AtomicReference<>(false);
        Map<Object, Class<?>[]> needValidate = Arrays.stream(parameters)
                .filter(param -> param.getAnnotation(JValidated.class) != null)
                .reduce(new HashMap<>(), (map, param) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        map.put(mapper.readValue(json, param.getType()), param.getAnnotation(JValidated.class).groups());
                    } catch (JsonProcessingException e) {
                        jsonError.set(true);
                    }
                    return map;
                }, (map, param) -> map);

        if (jsonError.get()) {
            return true;
        }
        Map<String, List<String>> errors = JValidator.doValidate(needValidate, jdbcTemplate);

        if (errors.size() > 0) {
            boolean hasBindingErrors = false;
            for (Parameter param : parameters) {
                if (param.getType().getName().equals(BindingErrors.class.getName())) {
                    hasBindingErrors = true;
                    request.setAttribute("jvalidation_binding_errors", new BindingErrors(errors));
                    break;
                }
            }
            if (!hasBindingErrors) {
                throw new ValidateFailedException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}

package com.xmxisme.product.filter;

import com.alibaba.fastjson.JSONObject;
import com.xmxisme.product.annotation.RequireRole;
import com.xmxisme.product.config.JwtTokenParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;


@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequireRole requireRole = handlerMethod.getMethodAnnotation(RequireRole.class);
            if (requireRole != null) {
                String[] requiredRoles = requireRole.value();
                String token = request.getHeader("Authorization");
                if (token == null || !token.startsWith("Bearer ")) {
                    sendErrorResponse(response, "Unauthorized", HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }
                String roleName = JwtTokenParser.getRoleNameFromToken(token.substring(7));
                System.out.println("roleName: " + roleName);
                boolean hasRequiredRole = false;
                for (String role : requiredRoles) {
                    if (Objects.equals(roleName, role)) {
                        hasRequiredRole = true;
                        break;
                    }
                }
                if (!hasRequiredRole) {
                    sendErrorResponse(response, "Insufficient permissions", HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            }
        }
        return true;
    }

    private void sendErrorResponse(HttpServletResponse response, String message, int statusCode) throws Exception {
        response.setContentType("application/json;charset=UTF-8"); // 设置正确的编码和内容类型
        response.setStatus(statusCode);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", statusCode);
        jsonObject.put("message", message);
        response.setHeader("Content-Length", Integer.toString(jsonObject.toString().length()));
        response.getWriter().write(jsonObject.toString());
        response.getWriter().flush();
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        // 可选实现
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 可选实现
    }
}




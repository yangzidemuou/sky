package com.sky.interceptor;
import com.sky.constant.JwtClaimsConstant;
import com.sky.context.ThreadLocalUtil;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        String token = request.getHeader(jwtProperties.getUserTokenName());
        //2.校验令牌
        try{
            log.info("jwt校验{}",token);
            Claims claims= JwtUtil.parseJWT(jwtProperties.getAdminSecret(),token);
            Long empId=Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());

            log.info("当前员工id:{}",empId);
            //存储当前操作线程的员工id
            ThreadLocalUtil.setCurrentId(empId);

            //通过
            return true;
        }catch (Exception e){
            //不通过返回401
            response.setStatus(401);
            return false;
        }
    }
}

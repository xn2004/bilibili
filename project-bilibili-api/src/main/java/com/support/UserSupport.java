package com.support;

import com.exception.ConditionException;
import com.service.UserService;
import com.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserSupport {

    @Autowired
    private UserService userService;

    public Long getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if(userId < 0) {
            throw new ConditionException("非法用户");
        }

        return userId;
    }

    //验证刷新令牌
    private void verifyRefreshToken(Long userId){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String refreshToken = requestAttributes.getRequest().getHeader("refreshToken");
        String dbRefreshToken = userService.getRefreshTokenByUserId(userId);
        if(!dbRefreshToken.equals(refreshToken)){
            throw new ConditionException("非法用户！");
        }
    }


}

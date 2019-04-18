package com.bamboovir.muresearchboost.app.middleware;

import com.bamboovir.muresearchboost.app.jwt.TokenUtils;
import com.bamboovir.muresearchboost.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

/*
HttpServletRequest request,
        HttpServletResponse response
 */

@Getter
@Setter
public class UserFunc {
    private User user;
    private Boolean isValid;
    private HttpServletRequest request;

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    public UserFunc(HttpServletRequest request){
        this.user = new User();
        this.request = request;
        this.isValid = null;
    }

    public UserFunc getUserThen(Consumer<User> consumer) {

        /*
        UserFunc falseUserFunc = new UserFunc(null);
        UserFunc trueUserFunc = new UserFunc(null);

        falseUserFunc.setIsValid(Boolean.FALSE);
        trueUserFunc.setIsValid(Boolean.TRUE);
        */

        String cookieAuthToken = Optional.ofNullable(this.getRequest().getCookies())
                .map(cookies -> Arrays.stream(cookies)
                        .filter((x) -> x.getName().equals(tokenHeader))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null)).orElse("");

        String authToken = Optional.ofNullable(this.getRequest().getHeader(this.tokenHeader)).orElse("");

        if (authToken.equals("")) {
            authToken = cookieAuthToken;
        }

        this.user.setUserName(this.tokenUtils.getUsernameFromToken(authToken));
        /*
        在Token中放入其他值
         */

        consumer.accept(this.user);
        return this;
    }

    public UserFunc and(Consumer<User> consumer) {
        if (this.getIsValid() == null || this.getUser() == null) {
            return getUserThen(consumer);
        }

        if (this.getIsValid()) {
            consumer.accept(this.user);
            return this;
        } else {
            return this;
        }
    }

}

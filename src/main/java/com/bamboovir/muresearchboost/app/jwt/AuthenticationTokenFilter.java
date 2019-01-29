package com.bamboovir.muresearchboost.app.jwt;

import com.bamboovir.muresearchboost.model.TokenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Max-Age", "180");

        String cookieAuthToken = Optional.ofNullable(httpRequest.getCookies())
                .map(cookies -> Arrays.stream(cookies)
                        .filter((x) -> x.getName().equals(tokenHeader))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null)).orElse("");

        String authToken = Optional.ofNullable(httpRequest.getHeader(this.tokenHeader)).orElse("");

        if (authToken.equals("")) {
            authToken = cookieAuthToken;
        }

        String username = this.tokenUtils.getUsernameFromToken(authToken);
        if ("GUEST".equals(username)) {
            System.out.println("GUEST !!");
            TokenUser guestTokenUser = new TokenUser();
            guestTokenUser.setAuthorities("GUEST");
            guestTokenUser.setEnable(Boolean.TRUE);
            guestTokenUser.setPassword("NULL");
            guestTokenUser.setUsername("GUEST");
            guestTokenUser.setLastPasswordChange(new Date().getTime() - 1000);
            UserDetails guestUserDetail = SecurityModelFactory.create(guestTokenUser);
            Authentication guestAuthentication =
                    new UsernamePasswordAuthenticationToken(guestUserDetail.getUsername(), guestUserDetail.getPassword(), guestUserDetail.getAuthorities());
            //guestAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(guestAuthentication);
            username = null;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.tokenUtils.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            if (!userDetails.isEnabled()) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print("{\"code\":\"452\",\"data\":\"\",\"message\":\"Your Account Has Been Locked\"}");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}

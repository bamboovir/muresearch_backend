package com.bamboovir.muresearchboost.app.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${testAPI}")
    private String apiBaseStr;

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }


    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();

        http.logout()
                .deleteCookies(tokenHeader)
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable() // Based on token, no csrf required
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // There is no need for a session in the system.
                .authorizeRequests()
                .antMatchers(apiBaseStr + "auth/**").permitAll() // token routing
                .antMatchers(apiBaseStr + "admin/**").hasAnyAuthority("ADMIN")
                .antMatchers(apiBaseStr + "**").hasAnyAuthority("GUEST", "ADMIN")
                .anyRequest().permitAll(); // Other APIs are protected with Token
        http.headers().cacheControl();
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                .authenticationEntryPoint(this.unauthorizedHandler)
                .accessDeniedHandler(this.accessDeniedHandler);
    }
}
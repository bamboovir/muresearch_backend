package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.jwt.TokenDetail;
import com.bamboovir.muresearchboost.app.jwt.TokenDetailImpl;
import com.bamboovir.muresearchboost.app.jwt.TokenUtils;
import com.bamboovir.muresearchboost.app.jwt.UserDetailsServiceImpl;
import com.bamboovir.muresearchboost.app.persistence.TokenUserRepository;
import com.bamboovir.muresearchboost.model.TokenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "${testAPI}auth")
public class TestAuthController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    @Value("${token.expiration}")
    private Long expiration;

    @Autowired
    private TokenUserRepository tokenUserRepository;

    @Value("${token.header}")
    private String tokenHeader;

    /*
    登陆
    return message Code
    单个 IP 限制请求次数
    单个账号限制请求次数
     */
    @PostMapping("/")
    public void login(@Valid @RequestBody TokenUser tokenUser){
        //tokenUserRepository.save(tokenUser);
    }


    @GetMapping("/guest")
    @ResponseBody
    public Map<String, String> getGuestToken(HttpServletRequest request, HttpServletResponse response) {
        TokenDetail tokenDetail = new TokenDetailImpl("GUEST");
        String guestToken = tokenUtils.generateToken(tokenDetail);
        Cookie tokenCookie = new Cookie(tokenHeader,guestToken);
        tokenCookie.setHttpOnly(Boolean.TRUE);
        //tokenCookie.setSecure(Boolean.TRUE); // When using HTTPS
        tokenCookie.setMaxAge(expiration.intValue());
        //tokenCookie.setDomain("localhost");// Set the domain name to use when cross-domain access
        tokenCookie.setPath("/");// Set the path, To use when sharing cookies
        response.addCookie(tokenCookie);
        return new HashMap<String, String>() {{
            put("Token", guestToken);
        }};
    }

    /*
    @PostMapping("/")
    public Map getToken() throws IOException {
        TokenDetail tokenDetail = new TokenDetailImpl("GUEST");
        String guestToken = tokenUtils.generateToken(tokenDetail);

        //Key key = TokenKey.getInstance().getKey();
        //String jws = Jwts.builder().setSubject(mapper.writeValueAsString(new User().mock())).signWith(key).compact();
        /*
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject();
        } catch (JwtException ignored) {

        }

        //return jws;
        return "";
    }
    */
}

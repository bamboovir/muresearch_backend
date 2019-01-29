package com.bamboovir.muresearchboost.app.apiv1;

import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";


    // text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
    // application/json
    //@GetMapping(value = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = PATH)
    public String error(HttpServletRequest request, HttpServletResponse response) {
        Boolean isJsonRequest = Optional.of(request.getHeader("accept") + request.getHeader("Accept"))
                .map(String::toLowerCase)
                .map(str -> str.contains("application/json"))
                .orElse(Boolean.FALSE);

        if(isJsonRequest){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            try {
                response.getWriter().println("{\"code\":404,\"message\":\"This page does not exist!\",\"data\":\"\"}");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //必须要有一个返回值
            return "";
        }else {
            //返回404文件的路径
            return "api.html";
        }

        /*
                .filter(str -> str.contains("application/json"))
                .<Runnable>map(value -> () -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    try {
                        response.getWriter().println("{\"code\":404,\"message\":\"This page does not exist!\",\"data\":\"\"}");
                        response.getWriter().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .orElse(() -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html");
                    try {
                        response.getWriter().println("404 Page");
                        response.getWriter().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .run();
                */
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
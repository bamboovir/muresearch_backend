package com.bamboovir.muresearchboost.app.apiv1;

import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${v1API}/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;


}

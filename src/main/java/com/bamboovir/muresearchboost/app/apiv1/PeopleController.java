package com.bamboovir.muresearchboost.app.apiv1;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="${v1API}/people")
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;

}

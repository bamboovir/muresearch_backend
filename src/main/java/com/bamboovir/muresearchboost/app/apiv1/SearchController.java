package com.bamboovir.muresearchboost.app.apiv1;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="${v1API}/search")
public class SearchController {
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;
}

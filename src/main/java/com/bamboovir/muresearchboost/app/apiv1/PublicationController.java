package com.bamboovir.muresearchboost.app.apiv1;

import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="${v1API}/publication")
public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;

}

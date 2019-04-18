package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;
import com.bamboovir.muresearchboost.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${testAPI}related")
public class TestRelatedController {

    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;

    @PostMapping("/people/{page}")
    public Message<List<People>> getRelatedPeopleList(@RequestBody People people
            , @PathVariable("page") Integer page){
        //peopleElasticSearchRepository.searchSimilar(people);
        return new Message<>();
    }

    @PostMapping("/publication/{page}")
    public Message<List<Publication>> getRelatedPublicationList(@RequestBody Publication publication
            , @PathVariable("page") Integer page){
        //publicationElasticSearchRepository.searchSimilar(publication);
        return new Message<>();
    }

}

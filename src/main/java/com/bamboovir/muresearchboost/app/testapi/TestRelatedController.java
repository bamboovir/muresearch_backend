package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.SubjectElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;
import com.bamboovir.muresearchboost.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    private SubjectElasticSearchRepository subjectElasticSearchRepository;

    @PostMapping("/people/{page}")
    public Message<List<People>> getRelatedPeopleList(@Valid @RequestBody People people
            , @PathVariable("page") Integer page){
        //peopleElasticSearchRepository.searchSimilar(people);
        return new Message<>();
    }

    @PostMapping("/publication")
    public Message<List<Publication>> getRelatedPublicationList(@Valid @RequestBody Publication publication
            , @PathVariable("page") Integer page){
        //peopleElasticSearchRepository.searchSimilar(people);
        return new Message<>();
    }

    @PostMapping("/subject")
    public Message<List<Subject>> getRelatedSubjectList(@Valid @RequestBody Subject subject
            , @PathVariable("page") Integer page){
        //peopleElasticSearchRepository.searchSimilar(people);
        return new Message<>();
    }

    @PostMapping("/user")
    public Message<List<User>> getRelatedUserList(@Valid @RequestBody User user
            , @PathVariable("page") Integer page){
        //peopleElasticSearchRepository.searchSimilar(people);
        return new Message<>();
    }

}

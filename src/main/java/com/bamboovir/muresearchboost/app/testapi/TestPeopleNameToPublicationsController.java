package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.persistence.PeopleNameToPublicationRepository;
import com.bamboovir.muresearchboost.model.PeopleNameToPublications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(path="${testAPI}/peoplenametopublication")
public class TestPeopleNameToPublicationsController {
    @Autowired
    private PeopleNameToPublicationRepository peopleNameToPublicationRepository;


    @GetMapping("/default")
    public PeopleNameToPublications getDefault(){
        return new PeopleNameToPublications().mock();
    }

    @GetMapping("/name/{name}")
    public Flux<PeopleNameToPublications> getPeopleNameToPublicationsByName(@PathVariable(value = "name") String name){
        return peopleNameToPublicationRepository.findAllByName(name);
    }

}


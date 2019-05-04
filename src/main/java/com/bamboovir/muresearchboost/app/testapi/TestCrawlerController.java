package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.PeopleNameToPublicationRepository;
import com.bamboovir.muresearchboost.app.persistence.PeopleRepository;
import com.bamboovir.muresearchboost.app.persistence.PublicationRepository;
import com.bamboovir.muresearchboost.model.Message;
import com.bamboovir.muresearchboost.model.People;
import com.bamboovir.muresearchboost.model.PeopleNameToPublications;
import com.bamboovir.muresearchboost.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${testAPI}crawler")
public class TestCrawlerController {
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    private PeopleNameToPublicationRepository peopleNameToPublicationRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;

    @PostMapping("/publication")
    public Message<Publication> insertPublication(@RequestBody Publication publication) {
        return new Message<Publication>()
                .setCode(200)
                .setData(publicationRepository
                        .save(publication)
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> publicationElasticSearchRepository.save(x))
                        .block());
    }

    @PostMapping("/publication/bulk")
    public Message<List<Publication>> insertBulkPublication(@RequestBody List<Publication> publicationList) {
        return new Message<List<Publication>>()
                .setCode(200)
                .setData(publicationRepository
                        .saveAll(publicationList)
                        .collect(Collectors.toList())
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> publicationElasticSearchRepository.saveAll(x))
                        .block());
    }

    @PostMapping("/peoplenametopublication/bulk")
    public Message<List<PeopleNameToPublications>> createPeopleNameToPublications(@RequestBody List<PeopleNameToPublications> peopleNameToPublicationsList) {
        return new Message<List<PeopleNameToPublications>>()
                .setCode(200)
                .setData(peopleNameToPublicationRepository
                        .saveAll(peopleNameToPublicationsList)
                        .collect(Collectors.toList())
                        .doOnError(Throwable::printStackTrace)
                        .block());
    }

    @PostMapping("/people")
    public Message<People> insertPeople(@RequestBody People people) {
        return new Message<People>()
                .setCode(200)
                .setData(peopleRepository
                        .save(people)
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> peopleElasticSearchRepository.save(x))
                        .block());
    }

    @PostMapping("/people/bulk")
    public Message<List<People>> insertBulkPeople(@RequestBody List<People> peopleList) {
        return new Message<List<People>>()
                .setCode(200)
                .setData(peopleRepository
                        .saveAll(peopleList)
                        .collect(Collectors.toList())
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> peopleElasticSearchRepository.saveAll(x))
                        .block());
    }

}

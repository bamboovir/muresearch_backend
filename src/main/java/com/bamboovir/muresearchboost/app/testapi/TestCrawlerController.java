package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.SubjectElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.PeopleRepository;
import com.bamboovir.muresearchboost.app.persistence.PublicationRepository;
import com.bamboovir.muresearchboost.app.persistence.SubjectRepository;
import com.bamboovir.muresearchboost.model.Message;
import com.bamboovir.muresearchboost.model.People;
import com.bamboovir.muresearchboost.model.Publication;
import com.bamboovir.muresearchboost.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "${testAPI}crawler")
public class TestCrawlerController {
    /*
    爬虫仅仅有权限向这几个库中添加内容
     */
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;
    @Autowired
    private SubjectElasticSearchRepository subjectElasticSearchRepository;

    /*
    对爬虫校验权限
    爬虫属于一个用户
    userfun.getuserthen()
     */
    @PostMapping("/publication")
    public Message<Publication> insertPublication(@Valid @RequestBody Publication publication) {
        return new Message<Publication>()
                .setCode(200)
                .setData(publicationRepository
                        .save(publication)
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> publicationElasticSearchRepository.save(x))
                        .block());
    }

    @PostMapping("/publication/bulk")
    public Message<List<Publication>> insertBulkPublication(@Valid @RequestBody List<Publication> publicationList) {
        return new Message<List<Publication>>()
                .setCode(200)
                .setData(publicationRepository
                        .saveAll(publicationList)
                        .collect(Collectors.toList())
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> publicationElasticSearchRepository.saveAll(x))
                        .block());
    }


    @PostMapping("/subject")
    public Message<Subject> insertSubject(@Valid @RequestBody Subject subject) {
        return new Message<Subject>()
                .setCode(200)
                .setData(subjectRepository
                        .save(subject)
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> subjectElasticSearchRepository.save(x))
                        .block());
    }

    @PostMapping("/subject/bulk")
    public Message<List<Subject>> insertBulkSubject(@Valid @RequestBody List<Subject> subjectList) {
        return new Message<List<Subject>>()
                .setCode(200)
                .setData(subjectRepository
                        .saveAll(subjectList)
                        .collect(Collectors.toList())
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> subjectElasticSearchRepository.saveAll(x))
                        .block());
    }

    @PostMapping("/people")
    public Message<People> insertPeople(@Valid @RequestBody People people) {
        return new Message<People>()
                .setCode(200)
                .setData(peopleRepository
                        .save(people)
                        .doOnError(Throwable::printStackTrace)
                        .doOnSuccess(x -> peopleElasticSearchRepository.save(x))
                        .block());
    }

    @PostMapping("/people/bulk")
    public Message<List<People>> insertBulkPeople(@Valid @RequestBody List<People> peopleList) {
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

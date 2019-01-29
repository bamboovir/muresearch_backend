package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.persistence.PublicationRepository;
import com.bamboovir.muresearchboost.app.persistence.SubjectRepository;
import com.bamboovir.muresearchboost.model.Publication;
import com.bamboovir.muresearchboost.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="${testAPI}subject")
public class TestSubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/default")
    public Subject getDefault(){
        return new Subject().mock();
    }

    /*
    通过ID获取Subject
    */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Subject>> getPublicationById(@PathVariable(value = "id") String id) {
        return subjectRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}

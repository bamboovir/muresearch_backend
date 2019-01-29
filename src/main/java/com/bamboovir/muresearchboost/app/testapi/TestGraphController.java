package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.model.Publication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "${testAPI}graph")
public class TestGraphController {
    /*
    GET /api/graph/user/ -> {}
    GET /api/graph/people/ -> {}
    GET /api/graph/subject/ -> {}
    GET /api/graph/publication/ -> {}
     */

    @GetMapping("/user/{userId}")
    public String getUserGraph(@PathVariable(value = "userId") String userId){
        return "";
    }

    @GetMapping("/people/{peopleId}")
    public String getPeopleGraph(@PathVariable(value = "peopleId") String peopleId){
        return "";
    }

    @GetMapping("/subject/{subjectId}")
    public String getSubjectGraph(@PathVariable(value = "subjectId") String subjectId){
        return "";
    }

    @GetMapping("/publication/{publicationId}")
    public String getPublicationGraph(@PathVariable(value = "publicationId") String publicationId){
        return "";
    }
}

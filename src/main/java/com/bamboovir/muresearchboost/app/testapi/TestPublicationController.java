package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.persistence.PublicationRepository;
import com.bamboovir.muresearchboost.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "${testAPI}publication")
public class TestPublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @GetMapping("/default")
    public Publication getDefaultPublication(){
        return new Publication().mock();
    }

    // db.getCollection('publication').aggregate([{ $sample: { size: 1 } }])
    @GetMapping("/")
    public Flux<Publication> getAllPublication() {
        return publicationRepository.findAll();
    }

    /*
    用户暂时不可以添加文章，主要靠爬虫
    需要鉴权
    ADMIN 直接过
    User 操作的属于自己的直接过
    其他Block
     */
    /*
    @PostMapping("/")
    public Mono<Publication> createPublication(@Valid @RequestBody Publication publication) {
        return publicationRepository.save(publication);
    }
    */

    /*
    通过ID获取出版物
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Publication>> getPublicationById(@PathVariable(value = "id") String id) {
        return publicationRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /*
    更新出版物
    暂时关闭这个功能
    需要鉴权
    User 操作的属于自己的直接过
    其他Block
     */
    /*
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Publication>> updatePublicationById(@PathVariable(value = "id") String id, @Valid @RequestBody Publication publication) {
        return publicationRepository.findById(id)
                .flatMap(pb -> {
                    pb = publication;
                    return publicationRepository.save(pb);
                }).map(updatedPublication -> new ResponseEntity<>(updatedPublication, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    */



}

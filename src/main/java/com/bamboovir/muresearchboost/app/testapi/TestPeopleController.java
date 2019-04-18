package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.PeopleRepository;
import com.bamboovir.muresearchboost.app.persistence.PublicationRepository;
import com.bamboovir.muresearchboost.model.People;
import com.bamboovir.muresearchboost.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(path="${testAPI}/people")
public class TestPeopleController {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;

    @GetMapping("/default")
    public People getDefault(){
        return new People().mock();
    }

    /*
    暴露给爬虫的 Input interface
    不需要给用户暴露 Input interface
    * 需要鉴权
    具有爬虫权限可以使用

    需要验证People是否Valid
     */
    @PostMapping("/")
    public Mono<People> createPublication(@RequestBody People people) {
        return peopleRepository.save(people).doOnSuccess(x -> peopleElasticSearchRepository.save(x));
    }

    /*
    通过ID获取People
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<People>> getPeopleById(@PathVariable(value = "id") String id) {
        return peopleRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /*
    @GetMapping("/default")
    public People getDefault(@RequestHeader("Auth") String auth){
        Key key = TokenKey.getInstance().getKey();
        System.out.println(auth);
        try {
            System.out.println(
                    Jwts.parser().setSigningKey(key).parseClaimsJws(auth).getBody().getSubject());
        } catch (JwtException ignored) {
            System.out.println("Error");
        }
        return new People().mock();
    }
    */
}

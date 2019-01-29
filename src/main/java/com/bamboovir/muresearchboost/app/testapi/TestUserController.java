package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.UserRepository;
import com.bamboovir.muresearchboost.model.Publication;
import com.bamboovir.muresearchboost.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="${testAPI}user")
public class TestUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;

    @GetMapping("/default")
    public User getDefault(){
        return new User().mock();
    }

    /*
    创造用户
    同时返回Token
    Token操作要求幂等性
     */
    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        return userRepository.save(user).doOnSuccess(x -> userElasticSearchRepository.save(x));
    }

    /*
    @GetMapping("/")
    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        userElasticSearchRepository.findAll().forEach(userList::add);
        return userList;
    }

    @PostMapping("/")
    public User createUser(@Valid @RequestBody User user){
        return userElasticSearchRepository.save(user);
    }
    */

    /*
    @GetMapping("/search/{name}")
    public List<User> getUserLike(@PathVariable(value = "name") String name){
        return userElasticSearchRepository.findByFullnameLike(name).collect(Collectors.toList());
    }
    */

    /*
    通过ID获取User
    */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getPublicationById(@PathVariable(value = "id") String id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}



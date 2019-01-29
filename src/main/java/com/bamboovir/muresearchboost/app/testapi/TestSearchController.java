package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.SubjectElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;

import static com.bamboovir.muresearchboost.tool.InputUtil.checkStringList;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.bamboovir.muresearchboost.model.*;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bamboovir.muresearchboost.tool.InputUtil;

@RestController
@RequestMapping(path = "${testAPI}search")
public class TestSearchController {

    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;
    @Autowired
    private SubjectElasticSearchRepository subjectElasticSearchRepository;

    @PostMapping("/")
    public Message<Map<String, List<? extends MuResearchModel>>> getSearchResultBySearchState(@Valid @RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        Map<String, List<? extends MuResearchModel>> result = new HashMap<>();
        result.put("people", getPeopleSearchResultBySearchState(searchState, page).getData());
        result.put("subject", getSubjectSearchResultBySearchState(searchState, page).getData());
        result.put("user", getUserSearchResultBySearchState(searchState, page).getData());
        result.put("publication", getUserSearchResultBySearchState(searchState, page).getData());
        return new Message<Map<String, List<? extends MuResearchModel>>>().setCode(200).setData(result);
    }

    @PostMapping("/people/{page}")
    public Message<List<People>> getPeopleSearchResultBySearchState(@Valid @RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        List<People> peopleList = new ArrayList<>();
        Pageable pageable = new PageRequest(page, 20);
        peopleElasticSearchRepository.search(
                new MultiMatchQueryBuilder(checkStringList(searchState.getRawSearchText()))
                        .fields(new HashMap<String, Float>() {{
                            put("career", (float) 0.25);
                            put("fullname", (float) 0.25);
                            put("email", (float) 0.25);
                            put("organization", (float) 0.25);
                        }})
                        .operator(Operator.OR)
                        .fuzziness(Fuzziness.AUTO) // 自动确认匹配级别
                        .maxExpansions(25) // 最大到25字
                        .prefixLength(3) // 必须满足前三字
                , pageable)
                .forEach(peopleList::add);
        return new Message<List<People>>().setCode(200).setData(peopleList);
    }

    @PostMapping("/user/{page}")
    public Message<List<User>> getUserSearchResultBySearchState(@Valid @RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        List<User> userList = new ArrayList<>();
        Pageable pageable = new PageRequest(page, 20);
        userElasticSearchRepository.search(
                new MultiMatchQueryBuilder(checkStringList(searchState.getRawSearchText()))
                        .fields(new HashMap<String, Float>() {{
                            put("career", (float) 0.2);
                            put("fullname", (float) 0.2);
                            put("email", (float) 0.2);
                            put("organization", (float) 0.2);
                            put("username", (float) 0.2);
                        }})
                        .operator(Operator.OR)
                        .fuzziness(Fuzziness.AUTO) // 自动确认匹配级别
                        .maxExpansions(25) // 最大到25字
                        .prefixLength(3) // 必须满足前三字
                , pageable)
                .forEach(userList::add);
        return new Message<List<User>>()
                .setCode(200)
                .setData(userList);
    }

    @PostMapping("/publication/{page}")
    public Message<List<Publication>> getPublicationSearchResultBySearchState(@Valid @RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        List<Publication> publicationList = new ArrayList<>();
        Pageable pageable = new PageRequest(page, 20);
        publicationElasticSearchRepository.search(
                new MultiMatchQueryBuilder(checkStringList(searchState.getRawSearchText()))
                        .fields(new HashMap<String, Float>() {{
                            put("abstractContent", (float) 0.25);
                            put("authors", (float) 0.25);
                            put("citation", (float) 0.25);
                            put("collection", (float) 0.25);
                            put("mainTitle", (float) 0.25);
                            put("subTitle", (float) 0.25);
                            put("subjects", (float) 0.1);
                        }})
                        .operator(Operator.OR)
                        .fuzziness(Fuzziness.AUTO) // 自动确认匹配级别
                        .maxExpansions(25) // 最大到25字
                        .prefixLength(3) // 必须满足前三字
                , pageable)
                .forEach(publicationList::add);
        return new Message<List<Publication>>()
                .setCode(200)
                .setData(publicationList);
    }

    @PostMapping("/subject/{page}")
    public Message<List<Subject>> getSubjectSearchResultBySearchState(@Valid @RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        List<Subject> subjectList = new ArrayList<>();
        Pageable pageable = new PageRequest(page, 20);
        subjectElasticSearchRepository.search(
                new MultiMatchQueryBuilder(checkStringList(searchState.getRawSearchText()))
                        .fields(new HashMap<String, Float>() {{
                            put("name", (float) 0.5);
                            put("detail", (float) 0.5);
                        }})
                        .operator(Operator.OR)
                        .fuzziness(Fuzziness.AUTO) // 自动确认匹配级别
                        .maxExpansions(25) // 最大到25字
                        .prefixLength(3) // 必须满足前三字
                , pageable)
                .forEach(subjectList::add);
        return new Message<List<Subject>>()
                .setCode(200)
                .setData(subjectList);
    }

    @GetMapping("/people/{page}/{rawText}")
    public Message<List<People>> getPeopleSearchResultByRawText(@PathVariable(value = "rawText") String rawText, @PathVariable("page") Integer page) {
        return getPeopleSearchResultBySearchState(new SearchState()
                .setRawSearchText(Stream.of(rawText.split(" "))
                        .collect(Collectors.toList())), page);
    }

    @GetMapping("/user/{page}/{rawText}")
    public Message<List<User>> getUserSearchResultByRawText(@PathVariable(value = "rawText") String rawText, @PathVariable("page") Integer page) {
        return getUserSearchResultBySearchState(new SearchState()
                .setRawSearchText(Stream.of(rawText.split(" "))
                        .collect(Collectors.toList())), page);
    }

    @GetMapping("/publication/{page}/{rawText}")
    public Message<List<Publication>> getPublicationSearchResultByRawText(@PathVariable(value = "rawText") String rawText, @PathVariable("page") Integer page) {
        return getPublicationSearchResultBySearchState(new SearchState()
                .setRawSearchText(Stream.of(rawText.split(" "))
                        .collect(Collectors.toList())), page);
    }

    @GetMapping("/subject/{page}/{rawText}")
    public Message<List<Subject>> getSubjectSearchResultByRawText(@PathVariable(value = "rawText") String rawText, @PathVariable("page") Integer page) {
        return getSubjectSearchResultBySearchState(new SearchState()
                .setRawSearchText(Stream.of(rawText.split(" "))
                        .collect(Collectors.toList())), page);
    }

    @GetMapping("/{page}/{rawText}")
    public Message<Map<String, List<? extends MuResearchModel>>> getSearchResultByRawText(@PathVariable(value = "rawText") String rawText
            , @PathVariable("page") Integer page) {
        return getSearchResultBySearchState(new SearchState()
                .setRawSearchText(Stream.of(rawText.split(" "))
                        .collect(Collectors.toList())), page);
    }

    @GetMapping("/default")
    public SearchState getDefaultSearchState() {
        return new SearchState().mock();
    }
}

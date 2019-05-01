package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;

import static com.bamboovir.muresearchboost.tool.InputUtil.checkStringList;

import com.bamboovir.muresearchboost.model.*;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "${testAPI}search")
public class TestSearchController {

    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;

    // https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.scroll
    @PostMapping("/{page}")
    public Message<Map<String, List<? extends MuResearchModel>>> getSearchResultBySearchState(@RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        Map<String, List<? extends MuResearchModel>> result = new HashMap<>();
        result.put("people", getPeopleSearchResultBySearchState(searchState, page).getData());
        result.put("publication", getPublicationSearchResultBySearchState(searchState, page).getData());
        return new Message<Map<String, List<? extends MuResearchModel>>>().setCode(200).setData(result);
    }

    @PostMapping("/people/{page}")
    public Message<List<People>> getPeopleSearchResultBySearchState(@RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        List<People> peopleList = new ArrayList<>();
        Pageable pageable = new PageRequest(page, 20);
        peopleElasticSearchRepository.search(
                new MultiMatchQueryBuilder(checkStringList(searchState.getRawSearchText()))
                        .fields(new HashMap<String, Float>() {{
                            put("firstName",(float) 0.20);
                            put("lastName",(float) 0.20);
                            put("career", (float) 0.20);
                            put("email", (float) 0.20);
                            put("organization", (float) 0.20);
                        }})
                        .operator(Operator.OR)
                        .fuzziness(Fuzziness.AUTO) // 自动确认匹配级别
                        .maxExpansions(25) // 最大到25字
                        .prefixLength(3) // 必须满足前三字
                , pageable)
                .forEach(peopleList::add);
        return new Message<List<People>>().setCode(200).setData(peopleList);
    }

    @PostMapping("/publication/{page}")
    public Message<List<Publication>> getPublicationSearchResultBySearchState(@RequestBody SearchState searchState
            , @PathVariable("page") Integer page) {
        List<Publication> publicationList = new ArrayList<>();
        Pageable pageable = new PageRequest(page, 20);
        publicationElasticSearchRepository.search(
                new MultiMatchQueryBuilder(checkStringList(searchState.getRawSearchText()))
                        .fields(new HashMap<String, Float>() {{
                            put("mainTitle", (float) 0.20);
                            put("abstractContent", (float) 0.20);
                            put("publicationType", (float) 0.20);
                            put("authors", (float) 0.20);
                            put("collection", (float) 0.20);
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

    @GetMapping("/people/{page}/{rawText}")
    public Message<List<People>> getPeopleSearchResultByRawText(@PathVariable(value = "rawText") String rawText, @PathVariable("page") Integer page) {
        return getPeopleSearchResultBySearchState(new SearchState()
                .setRawSearchText(Stream.of(rawText.split(" "))
                        .collect(Collectors.toList())), page);
    }

    @GetMapping("/publication/{page}/{rawText}")
    public Message<List<Publication>> getPublicationSearchResultByRawText(@PathVariable(value = "rawText") String rawText, @PathVariable("page") Integer page) {
        return getPublicationSearchResultBySearchState(new SearchState()
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

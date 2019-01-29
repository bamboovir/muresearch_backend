package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.SubjectElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;
import com.bamboovir.muresearchboost.app.exception.CustomSecurityException;
import com.bamboovir.muresearchboost.model.People;
import com.bamboovir.muresearchboost.model.Publication;
import com.bamboovir.muresearchboost.model.Subject;
import com.bamboovir.muresearchboost.model.User;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.fuzzyQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "${testAPI}autocomplete")
public class TestAutoCompleteController {
    /*
    自动补全主要根据用户的搜索历史来进行填充
    搜索历史应该可以删除
     */

    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;
    @Autowired
    private SubjectElasticSearchRepository subjectElasticSearchRepository;



    /*
    GET /api/autocomplete/ string -> {}
    GET /api/autocomplete/people/ string -> {}
    GET /api/autocomplete/user/ string -> {}
    GET /api/autocomplete/publication/ string -> {}
    GET /api/autocomplete/subject/ string -> {}
     */

    @GetMapping("/people/{querystr}")
    public List<People> autoCompletePeople(@PathVariable(value = "querystr") String queryStr){
        List<People> peopleList = new ArrayList<>();
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryStr);
        //BoolQueryBuilder boolqueryBuilder = new BoolQueryBuilder();

        /*
        boolqueryBuilder.should(QueryBuilders
                .queryStringQuery("*"+str+"*")
                .defaultOperator(Operator.AND).analyzeWildcard(true));

        */
        //peopleElasticSearchRepository.search(new BoolQueryBuilder().should(QueryBuilders.fuzzyQuery("organization",queryStr))
                //.should(QueryBuilders.fuzzyQuery("email",queryStr))).forEach(peopleList::add);
        //peopleElasticSearchRepository.search(new MultiMatchQueryBuilder(queryStr,"organization","email")).forEach(peopleList::add);
        //peopleElasticSearchRepository.search(builder).forEach(peopleList::add);
        //寻找类似的人
        //peopleElasticSearchRepository.searchSimilar();
         /*
        peopleElasticSearchRepository.search(queryStringQuery(queryStr)
                .enablePositionIncrements(Boolean.TRUE)
                .lenient(Boolean.TRUE)
                .useAllFields(Boolean.TRUE)
                .fuzziness(Fuzziness.TWO)
                .fuzzyPrefixLength(3)
                .fuzzyMaxExpansions(20)
                .splitOnWhitespace(Boolean.TRUE)).forEach(peopleList::add);
        */

        peopleElasticSearchRepository.search(new MultiMatchQueryBuilder(queryStr)
                .field("_all")
                .fuzziness(Fuzziness.AUTO)).forEach(peopleList::add);

        return peopleList;
    }

    @GetMapping("/user/{querystr}")
    public List<User> autoCompleteUser(@PathVariable(value = "querystr") String queryStr){
        List<User> userList = new ArrayList<>();
        return userList;
    }

    @GetMapping("/publication/{querystr}")
    public List<Publication> autoCompletePublication(@PathVariable(value = "querystr") String queryStr){
        List<Publication> publicationList = new ArrayList<>();
        return publicationList;
    }

    @GetMapping("/subject/{querystr}")
    public List<Subject> autoCompleteSubject(@PathVariable(value = "querystr") String queryStr){
        List<Subject> subjectList = new ArrayList<>();
        return subjectList;
    }

    @GetMapping("/{querystr}")
    public Map<String,Object> autoComplete(@PathVariable(value = "querystr") String queryStr){

        return new HashMap<>();
    }







}

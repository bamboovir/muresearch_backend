package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.elasticsearch.PeopleElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.PublicationElasticSearchRepository;
import com.bamboovir.muresearchboost.app.elasticsearch.UserElasticSearchRepository;
import com.bamboovir.muresearchboost.model.People;
import com.bamboovir.muresearchboost.model.Publication;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "${testAPI}autocomplete")
public class TestAutoCompleteController {
    @Autowired
    private UserElasticSearchRepository userElasticSearchRepository;
    @Autowired
    private PeopleElasticSearchRepository peopleElasticSearchRepository;
    @Autowired
    private PublicationElasticSearchRepository publicationElasticSearchRepository;

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

    @GetMapping("/publication/{querystr}")
    public List<Publication> autoCompletePublication(@PathVariable(value = "querystr") String queryStr){
        List<Publication> publicationList = new ArrayList<>();
        return publicationList;
    }

    @GetMapping("/{querystr}")
    public Map<String,Object> autoComplete(@PathVariable(value = "querystr") String queryStr){

        return new HashMap<>();
    }







}

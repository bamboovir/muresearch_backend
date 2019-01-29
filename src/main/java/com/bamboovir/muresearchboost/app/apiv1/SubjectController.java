package com.bamboovir.muresearchboost.app.apiv1;

import com.bamboovir.muresearchboost.app.elasticsearch.SubjectElasticSearchRepository;
import com.bamboovir.muresearchboost.app.persistence.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="${v1API}/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectElasticSearchRepository subjectElasticSearchRepository;

    // 获得父领域
    // 获得子领域
    // 获得同级领域
}

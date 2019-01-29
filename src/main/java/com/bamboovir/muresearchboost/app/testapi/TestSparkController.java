package com.bamboovir.muresearchboost.app.testapi;

import com.bamboovir.muresearchboost.app.spark.Count;
import com.bamboovir.muresearchboost.app.spark.WordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



//@RestController
//@RequestMapping(path = "${testAPI}spark")
public class TestSparkController {
    /*
    @Autowired
    WordCount wordCount;

    @GetMapping("/")
    public ResponseEntity<List<Count>> words() {
        System.out.println("Spark!!");
        return new ResponseEntity<>(wordCount.count(), HttpStatus.OK);
    }
    */
}

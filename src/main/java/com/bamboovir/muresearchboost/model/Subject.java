package com.bamboovir.muresearchboost.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subject")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "subject", type = "subject", replicas = 1, refreshInterval = "-1")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject implements MuResearchModel{
    @Id
    private String id;
    private String name;
    private String detail;
    private String imageUrl;

    public Subject mock(){
        return new Subject()
                .setId("666")
                .setDetail("Computer science is the study of the theory, " +
                        "experimentation, and engineering that form the " +
                        "basis for the design and use of computers. It " +
                        "is the scientific and practical approach to computation " +
                        "and its applications and the systematic study of the feasibility," +
                        " structure, expression, and mechanization of the methodical " +
                        "procedures (or algorithms) that underlie the acquisition, " +
                        "representation, processing, storage, communication of, " +
                        "and access to, information. An alternative, more succinct " +
                        "definition of computer science is the study of automating algorithmic " +
                        "processes that scale. A computer scientist specializes in the theory of" +
                        " computation and the design of computational systems.[1]" +
                        " See glossary of computer science.")
                .setImageUrl("http://google.com")
                .setName("Computer Science");
    }
}

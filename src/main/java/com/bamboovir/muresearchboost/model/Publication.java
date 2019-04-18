package com.bamboovir.muresearchboost.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;


@Document(collection = "publication")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "publication", type = "publication", replicas = 1, refreshInterval = "-1")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Publication implements MuResearchModel {
    @Id
    private String id;
    private String mainTitle;
    private String abstractContent;
    private String originUrl;
    private String publicationType;
    private Date publishDate;
    private List<String> collections;
    private List<String> authors;
    private Map<String, String> otherId;


    public Publication mock() {
        return new Publication()
                .setId("1")
                .setMainTitle("Hello Title")
                .setAbstractContent("abstractContent")
                .setOriginUrl("https://www.google.com")
                .setPublicationType("PublicationType")
                .setPublishDate(new Date())
                .setCollections(Arrays.asList("A Collection", "Other"))
                .setAuthors(Arrays.asList("Ding Hao", "Huiming Sun"))
                .setOtherId(new HashMap<String, String>(){{
                        put("IEEE", "123456");
                        put("MOSPACE", "123456");
                }});
    }
}

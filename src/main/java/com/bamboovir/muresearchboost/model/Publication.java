package com.bamboovir.muresearchboost.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document(collection = "publication")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "publication", type = "publication", replicas = 1, refreshInterval = "-1")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Publication implements MuResearchModel {
    @Id
    private String id;
    private Map<String, String> otherId;
    private String mainTitle;
    private String subTitle;
    private String abstractContent;
    private String content;
    private List<ContentFile> contentFiles;
    private List<String> references;
    private String originUrl;
    private List<String> citation;
    private List<String> collections;
    private List<People> authors;
    private Date publishDate;
    private Date submitDate;
    private List<String> publisher;
    private List<Subject> subjectIds;
    private String language;
    private String publicationType;


    public Publication mock() {
        return new Publication()
                .setId("666").setAbstractContent("abstractContent")
                .setCitation(Stream.of("A Citation", "Other").collect(Collectors.toList()))
                .setCollections(Stream.of("A Collection", "Other").collect(Collectors.toList()))
                .setContent("Some Content")
                .setLanguage("English")
                .setMainTitle("Hello Title")
                .setOriginUrl("https://www.google.com")
                .setOtherId(new ConcurrentHashMap<String, String>() {{
                    put("IEEE", "12312341");
                    put("MOSPACE", "1234123");
                    put("WIKI", "345634563");
                }})
                .setPublicationType("PublicationType")
                .setPublishDate(new Date())
                .setPublisher(Stream.of("A Publisher", "Other").collect(Collectors.toList()))
                .setReferences(Stream.of("A Reference", "Other").collect(Collectors.toList()))
                .setSubmitDate(new Date()).setSubTitle("Subtitle");
    }
}

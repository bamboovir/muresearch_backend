package com.bamboovir.muresearchboost.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document(collection = "people_name_to_publications")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PeopleNameToPublications implements MuResearchModel{
    @Id
    private String id;
    private String name;
    private List<Publication> publications;

    public PeopleNameToPublications mock() {
        return new PeopleNameToPublications()
                .setId("1")
                .setName("Ding Hao")
                .setPublications(
                        Stream.of(new Publication().mock(),
                                  new Publication().mock())
                                .collect(Collectors.toList())
                );
    }
}

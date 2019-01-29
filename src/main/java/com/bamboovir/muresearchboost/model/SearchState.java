package com.bamboovir.muresearchboost.model;
import com.bamboovir.muresearchboost.app.Enum.SearchType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document(collection = "searchstate")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchState implements MuResearchModel{
    private String id;
    private User behaviorPromoter;
    private Integer pages;
    private List<User> users;
    private List<People> peoples;
    private Boolean precisionMatchingMode;
    private List<Subject> subjects;
    private List<Publication> publications;
    private List<String> rawSearchText;
    private List<SearchState> searchHistory;
    private List<String> scope;
    private List<String> searchType;

    public SearchState mock(){
        return new SearchState()
                .setId("666")
                .setRawSearchText(Stream.of("Deep Learning","ML").collect(Collectors.toList()))
                .setSearchType(Stream.of("People","Publication").collect(Collectors.toList()))
                .setBehaviorPromoter(new User().mock())
                .setPeoples(Stream.of(new People().mock(),new People().mock()).collect(Collectors.toList()))
                .setPrecisionMatchingMode(Boolean.TRUE)
                .setUsers(Stream.of(new User().mock(),new User().mock()).collect(Collectors.toList()))
                .setPublications(Stream.of(new Publication().mock(),new Publication().mock()).collect(Collectors.toList()))
                .setSearchHistory(Stream.of(new SearchState(),new SearchState()).collect(Collectors.toList()))
                .setScope(Stream.of("Computer Science","Deep Learning").collect(Collectors.toList()))
                .setSearchType(Stream.of(SearchType.SUBJECT.toString(),SearchType.PEOPLE.toString()).collect(Collectors.toList()))
                .setSubjects(Stream.of(new Subject().mock(),new Subject().mock()).collect(Collectors.toList()));
    }
}

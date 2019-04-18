package com.bamboovir.muresearchboost.model;
import com.bamboovir.muresearchboost.app.muenum.Scope;
import com.bamboovir.muresearchboost.app.muenum.SearchType;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SearchState implements MuResearchModel{
    private Integer pages;
    private String scope;
    private Publication publications;
    private List<String> peoples;
    private List<String> rawSearchText;
    private List<String> searchType;

    public SearchState mock(){
        return new SearchState()
                .setPublications(new Publication().mock())
                .setScope(Scope.MOSPACE.toString())
                .setRawSearchText(Stream.of("Deep Learning","ML").collect(Collectors.toList()))
                .setSearchType(Stream.of(SearchType.PUBLICATION.toString(),SearchType.PEOPLE.toString()).collect(Collectors.toList()));
    }
}

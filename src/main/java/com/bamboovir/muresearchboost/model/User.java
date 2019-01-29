package com.bamboovir.muresearchboost.model;

import com.bamboovir.muresearchboost.app.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document(collection = "user")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "user", type = "user", replicas = 1, refreshInterval = "-1")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements MuResearchModel{
    @Id
    private String id;
    private List<String> roles;
    private String gender;
    private String username;
    private String fullname;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private Date createdAt;
    private String imageMediumUrl;
    private String imageSmailUrl;
    private String imageLargeUrl;
    private List<String> career;
    private List<String> organization;
    private String information;
    private List<String> authorities;
    private Long lastPasswordChange;
    private Boolean enable;

    public User mock() {
        return new User()
                .setId("666")
                .setRoles(Stream.of("GUEST","USER","ADMIN").collect(Collectors.toList()))
                .setAuthorities(Stream.of("GUEST","USER").collect(Collectors.toList()))
                .setEnable(Boolean.TRUE)
                .setLastPasswordChange(12345L)
                .setEmail("hd945@mail.missouri.edu")
                .setCreatedAt(new Date()).setFirstname("Ding").setFullname("Ding Hao")
                .setLastname("Hao")
                .setGender(Gender.MALE.toString())
                .setUsername("hd945")
                .setImageLargeUrl("http://google.com")
                .setImageMediumUrl("http://google.com")
                .setImageSmailUrl("http://google.com")
                .setInformation("Hello World")
                .setOrganization(Stream.of("University Of Missouri", "Google").collect(Collectors.toList()))
                .setCareer(Stream.of("Professor", "Enginerr").collect(Collectors.toList()))
                .setPhonenumber("573123412431");
    }
}

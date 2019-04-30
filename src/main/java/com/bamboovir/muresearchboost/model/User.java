package com.bamboovir.muresearchboost.model;

import com.bamboovir.muresearchboost.app.muenum.Gender;
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
public class User implements MuResearchModel{
    @Id
    private String id;
    private String gender;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String information;
    private String imageUrl;
    private Boolean enable;
    private Date createdAt;
    private Date lastPasswordChange;
    private List<String> roles;
    private List<String> career;
    private List<String> organization;
    private List<String> authorities;

    public User mock() {
        return new User()
                .setId("666")
                .setGender(Gender.MALE.toString())
                .setUserName("hd945")
                .setFirstName("Ding")
                .setLastName("Hao")
                .setEmail("hd945@mail.missouri.edu")
                .setInformation("Hello World")
                .setEnable(Boolean.TRUE)
                .setLastPasswordChange(new Date())
                .setCreatedAt(new Date())
                .setOrganization(Stream.of("University Of Missouri", "Google").collect(Collectors.toList()))
                .setCareer(Stream.of("Professor", "Engineer").collect(Collectors.toList()))
                .setRoles(Stream.of("GUEST","USER","ADMIN").collect(Collectors.toList()))
                .setAuthorities(Stream.of("GUEST","USER").collect(Collectors.toList()));
    }
}

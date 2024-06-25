package com.muhammet.document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserProfile {
    @Id
    String id;
    Long authId;
    String userName;
    String name;
    String surName;
    String fullName;
    String about;
    String webSite;
    Integer age;
    String phone;
    String profileURL;
    String avatar;

}

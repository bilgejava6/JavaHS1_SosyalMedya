package com.muhammet.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "userprofile")
public class UserProfile {
    @Id
    String id;
    String ad;
    String soyad;
    String adres;
    String telefon;
    Integer yas;
    Long createAt;
}

package com.muhammet.document;

import com.muhammet.utility.State;
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
public class Post {
    @Id
    String id;
    Long authId;
    String userName;
    String imageUrl;
    String comment;
    Long date;
    Long likeCount;
    Long commentCount;
    State state;
}

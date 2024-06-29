package com.muhammet.repository;

import com.muhammet.document.UserProfile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserProfileRepository extends ElasticsearchRepository<UserProfile,String> {
}

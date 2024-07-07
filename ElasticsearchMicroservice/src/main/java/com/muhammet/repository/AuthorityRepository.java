package com.muhammet.repository;

import com.muhammet.document.Authority;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface AuthorityRepository extends ElasticsearchRepository<Authority,String> {
    Optional<Authority> findOptionalByAuthId(Long authId);
}

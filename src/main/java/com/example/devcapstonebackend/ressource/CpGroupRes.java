package com.example.devcapstonebackend.ressource;

import java.util.List;

import com.example.devcapstonebackend.model.CodepointGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "codepoint-groups", path = "codepoint-groups")
public interface CpGroupRes extends PagingAndSortingRepository<CodepointGroup, Long> {

    List<CodepointGroup> findByName(@Param("name") String name);

}
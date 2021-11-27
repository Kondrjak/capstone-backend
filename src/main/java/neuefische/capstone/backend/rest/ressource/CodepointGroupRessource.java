package neuefische.capstone.backend.rest.ressource;

import neuefische.capstone.backend.rest.model.CodepointGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "codepoint-group", path = "codepoint-group")
public interface CodepointGroupRessource extends PagingAndSortingRepository<CodepointGroup, Long> {
    List<CodepointGroup> findByName(@Param("name") String name);
}
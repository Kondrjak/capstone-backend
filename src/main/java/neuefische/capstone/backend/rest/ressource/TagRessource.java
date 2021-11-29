package neuefische.capstone.backend.rest.ressource;

import neuefische.capstone.backend.rest.model.CodepointGroup;
import neuefische.capstone.backend.rest.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface TagRessource extends PagingAndSortingRepository<Tag, Long> {
    List<CodepointGroup> findByName(@Param("name") String name);
}
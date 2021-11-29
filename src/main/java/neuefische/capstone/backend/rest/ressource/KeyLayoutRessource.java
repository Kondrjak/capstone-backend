package neuefische.capstone.backend.rest.ressource;

import neuefische.capstone.backend.rest.model.CodepointGroup;
import neuefische.capstone.backend.rest.model.KeyLayout;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "key-layout", path = "key-layout")
public interface KeyLayoutRessource extends PagingAndSortingRepository<KeyLayout, Long> {
    List<CodepointGroup> findByName(@Param("name") String name);
}
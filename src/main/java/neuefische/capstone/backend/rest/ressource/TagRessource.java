package neuefische.capstone.backend.rest.ressource;

import neuefische.capstone.backend.rest.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface TagRessource extends PagingAndSortingRepository<Tag, Long> {
    List<Tag> findByName(@Param("name") String name);
}
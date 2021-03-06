package neuefische.capstone.backend.rest.ressource;

import neuefische.capstone.backend.rest.model.Font;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "font", path = "font")
public interface FontRessource extends PagingAndSortingRepository<Font, Long> {
    List<Font> findByName(@Param("name") String name);
}

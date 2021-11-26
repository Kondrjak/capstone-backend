package neuefische.capstone.backend.ressource;

import neuefische.capstone.backend.model.CodepointGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "codepoint-groups", path = "codepoint-groups")
public interface CpGroupRes extends PagingAndSortingRepository<CodepointGroup, Long> {

    List<CodepointGroup> findByName(@Param("name") String name);

}
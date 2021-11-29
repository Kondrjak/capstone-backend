package neuefische.capstone.backend.testUtil.view;

import neuefische.capstone.backend.rest.model.Tag;
import org.springframework.hateoas.Link;

import java.util.List;

public class TagView extends Tag{
    List<Tag> content;
    List<Link> links;
}

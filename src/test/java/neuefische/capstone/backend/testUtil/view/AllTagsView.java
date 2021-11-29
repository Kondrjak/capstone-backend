package neuefische.capstone.backend.testUtil.view;

import org.springframework.hateoas.Links;

import java.util.List;

public class AllTagsView {
    Links links;
    List<TagView> content;
    Page page;
}

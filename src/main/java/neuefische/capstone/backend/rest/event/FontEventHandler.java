package neuefische.capstone.backend.rest.event;

import lombok.extern.slf4j.Slf4j;
import neuefische.capstone.backend.rest.model.Font;
import org.springframework.data.rest.core.annotation.*;

/**
 * The REST exporter emits eight different events throughout the process of working with an entity:
 *     BeforeCreateEvent
 *     AfterCreateEvent - fires on POST
 *     BeforeSaveEvent - fires on PUT and PATCH
 *     AfterSaveEvent
 *     BeforeLinkSaveEvent
 *     AfterLinkSaveEvent
 *     BeforeDeleteEvent
 *     AfterDeleteEvent
 */
@Slf4j
@RepositoryEventHandler
public class FontEventHandler {

    @HandleBeforeSave
    public void checkForRepetitions(Font group) {
        log.error("BeforeSave event fired for CodepointGroup: " + group.toString());
    }

    @HandleBeforeCreate
    public void checkForRepetitions2(Font group) {
        log.error("BeforeCreate event fired for CodepointGroup: " + group.toString());
    }

    @HandleBeforeDelete
    public void checkIfProblems(Font group) {
        log.error("BeforeDelete event fired for CodepointGroup: " + group.toString());
    }

    @HandleBeforeLinkSave
    public void checkLinksToSave(Font group) {
        log.error("BeforeLinkSave event fired for CodepointGroup: " + group.toString());
    }

    @HandleBeforeLinkDelete
    public void checkLinksToDelete(Font group) {
        log.error("BeforeLinkDelete event fired for CodepointGroup: " + group.toString());
    }

    @HandleAfterSave
    public void checkForRepetitions3(Font group) {
        log.error("AfterSave event fired for CodepointGroup: " + group.toString());
    }

    @HandleAfterCreate
    public void checkForRepetitions22(Font group) {
        log.error("AfterCreate event fired for CodepointGroup: " + group.toString());
    }

    @HandleAfterDelete
    public void checkIfProblems2(Font group) {
        log.error("AfterDelete event fired for CodepointGroup: " + group.toString());
    }

    @HandleAfterLinkSave
    public void checkLinksToSave2(Font group) {
        log.error("AfterLinkSave event fired for CodepointGroup: " + group.toString());
    }

    @HandleAfterLinkDelete
    public void checkLinksToDelete2(Font group) {
        log.error("AfterLinkDelete event fired for CodepointGroup: " + group.toString());
    }

}

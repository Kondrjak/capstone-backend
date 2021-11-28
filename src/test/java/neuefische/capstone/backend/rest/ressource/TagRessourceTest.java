package neuefische.capstone.backend.rest.ressource;

import neuefische.capstone.backend.rest.model.CodepointGroup;
import neuefische.capstone.backend.rest.model.Font;
import neuefische.capstone.backend.rest.model.Tag;
import neuefische.capstone.backend.security.storage.CredentialRepo;
import neuefische.capstone.backend.security.storage.PrivilegeRepo;
import neuefische.capstone.backend.security.storage.RoleRepo;
import neuefische.capstone.backend.testUtil.RequestBuilder;
import neuefische.capstone.backend.testUtil.TestJwtHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TagRessourceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestJwtHeaders jwtHeadersCreator;

    private HttpHeaders jwtHeaders;

    @Autowired
    CredentialRepo credentialRepo;

    @Autowired
    PrivilegeRepo privilegeRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    private FontRessource fontRepo;

    @Autowired TagRessource tagRepo;

    @Autowired CodepointGroupRessource codepointGroupRepo;

    @AfterEach
    public void clearRepos(){
        credentialRepo.deleteAll();
        roleRepo.deleteAll();
        privilegeRepo.deleteAll();
        codepointGroupRepo.deleteAll();
        fontRepo.deleteAll();
        tagRepo.deleteAll();
    }


    @BeforeAll
    public void assignJwtHeaders(){
        jwtHeaders = jwtHeadersCreator.createAdminAndReturnHeaders(port);
    }


    @Test
    public void postNonExistentTag_shouldSaveTheTag_thenGetAll_shouldContainTheTag(){
        // GIVEN
        Tag newTag = Tag.builder().name("mobile").build();
        RequestBuilder<Tag> request = new RequestBuilder<>(Tag.class, jwtHeaders, port);
        // WHEN
        ResponseEntity<Tag> postResponse = request.postForEntity("tag", newTag);
        // THEN
        assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(postResponse.getBody(), is(newTag));
        // WHEN
        RequestBuilder<String> getAllRequest = new RequestBuilder<>(String.class, jwtHeaders, port);
        ResponseEntity<String> getResponse = getAllRequest.getForEntity("tag");
        String actualBody = getResponse.getBody();
        // THEN
        assertThat(getResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(actualBody, containsString("mobile"));
    }

    @Test
    public void postExistentTag_shouldFailWithHttpStatus(){
        // GIVEN
        Tag tag = Tag.builder().name("numbers").build();
        RequestBuilder<Tag> request = new RequestBuilder<>(Tag.class, jwtHeaders, port);
        request.postForEntity("tag", tag);
        Tag existentTag = tag;
        // WHEN
        try{
            request.postForEntity("tag", existentTag);
            fail("There should had been an error, but was not");
        } catch (Exception e){
            assertTrue(e.getMessage().length()>20);
        }
    }

    @Test
    public void deleteExistentTag_withoutBindingToOtherEntities_shouldDeleteTag(){
        // GIVEN
        Tag tag = Tag.builder().name("veryUniqueTestTag").build();
        Tag tagWithId = tagRepo.save(tag);
        // WHEN
        RequestBuilder<String> deleteRequest = new RequestBuilder<>(String.class, jwtHeaders, port);
        ResponseEntity<String> deleteResponse = deleteRequest.deleteForEntity("tag/"+tagWithId.getId());
        // THEN
        assertThat(deleteResponse.getStatusCode(), is(HttpStatus.NO_CONTENT)); //Statuscode: 204
    }

    @Test
    public void deleteExistentTag_withBindingToOtherEntities_shouldFail(){
        // GIVEN
        Tag tag = Tag.builder().name("letters").build();
        Tag tagWithId = tagRepo.save(tag);
        Font font = Font.builder().name("Verdana").build();
        Font fontWithId = fontRepo.save(font);
        CodepointGroup cp = CodepointGroup.builder()
                .codepoints(List.of("a","b","c"))
                .name("small alphabet")
                .tags(List.of(tagWithId))
                .fonts(List.of(fontWithId))
                .build();
        codepointGroupRepo.save(cp);
        // WHEN
        RequestBuilder<String> deleteRequest = new RequestBuilder<>(String.class, jwtHeaders, port);
        try {
            deleteRequest.deleteForEntity("tag/"+tagWithId.getId());
            fail("There should had been an error, but was not");
        }catch (Exception e){
            assertThat(e.getMessage(), containsString("409"));
        }
    }
}
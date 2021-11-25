package neuefische.capstone.backend.testUtils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Refactored from
 * ResponseEntity<Tod[]> response =
 *      restTemplate.exchange(  "/api/tod/1",
 *                              HttpMethod.PUT,
 *                              new HttpEntity<>(updatedTodo, getHttpHeadersWithJWT()),
 *                              Tod.class);
 */
public class RequestBuilder<Type> {
    String path;
    HttpMethod method;
    HttpHeaders header;
    Type body;
    Class<Type> bodyType;

    /**
     * @return a response entity
     */
    public ResponseEntity<Type> send(){
        assertNotNull(path);
        assertNotNull(method);
        return (new RestTemplate()).exchange(path,method, new HttpEntity<>(body, header), bodyType);
    }

    /**
     * New entity builder. Ready to build response entities of given type.
     * The builder remembers the last build and might be refreshed by the newBuild()
     * or partly refreshed by newBuildSameHeaders() method.
     */
    public RequestBuilder(Class<Type> bodyType, HttpHeaders header){
        this.bodyType = bodyType;
        this.header = header;
        this.bodyType = bodyType;
    }

    /**
     * Sets the path of the response entity to build
     * @param path - to url
     * @return the builder
     */
    public RequestBuilder<Type> path(String path) {
        this.path = "http://localhost:"+path;
        return this;
    }

    /**
     * Sets the http method of the desired response entity
     * @param method
     * @return the builder
     */
    public RequestBuilder<Type> with(HttpMethod method) {
        this.method = method;
        return this;
    }

    /**
     *
     * @param url
     * @return
     */
    public ResponseEntity<Type> getForEntity(String url) {
        return with(HttpMethod.GET).path(url).send();
    }

    public ResponseEntity<Type> postForEntity(String url, Type questionToAdd) {
        this.body = questionToAdd;
        return with(HttpMethod.POST).path(url).send();
    }
}

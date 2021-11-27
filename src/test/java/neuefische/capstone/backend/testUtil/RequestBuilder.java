package neuefische.capstone.backend.testUtil;

import neuefische.capstone.backend.testUtil.view.TagView;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private String path;
    private HttpMethod method;
    private HttpHeaders header;
    private Type body;
    private Class<Type> bodyType;
    private int port;

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
    public RequestBuilder(Class<Type> bodyType, HttpHeaders header, int port){
        this.bodyType = bodyType;
        this.header = header;
        this.port = port;
    }

    /**
     * Sets the path of the response entity to build to "http://localhost:"+port+"/"+relativePath
     * @param relativePath - to url
     * @return the builder
     */
    public RequestBuilder<Type> pathCompletion(String relativePath) {
        this.path = "http://localhost:"+port+"/"+relativePath;
        return this;
    }

    /**
     * Sets the path of the response entity to build
     * @param path - to url
     * @return the builder
     */
    public RequestBuilder<Type> path(String path){
        this.path = path;
        return this;
    }

    /**
     * Sets the http method of the desired response entity to
     * @param method
     * @return the builder
     */
    public RequestBuilder<Type> with(HttpMethod method) {
        this.method = method;
        return this;
    }

    /**
     * Builds a get request response entity to the path "http://localhost:"+port+"/"+relPath
     * @param relPath - relative path following root/
     * @return ResponseEntity
     */
    public ResponseEntity<Type> getForEntity(String relPath) {
        return with(HttpMethod.GET).pathCompletion(relPath).send();
    }

    public ResponseEntity<Type> postForEntity(String relPath, Type postBody) {
        this.body = postBody;
        return with(HttpMethod.POST).pathCompletion(relPath).send();
    }

    public ResponseEntity<Type> deleteForEntity(String relPath) {
        return with(HttpMethod.DELETE).pathCompletion(relPath).send();
    }
}

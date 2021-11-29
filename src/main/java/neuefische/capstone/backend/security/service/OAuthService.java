package neuefische.capstone.backend.security.service;

import neuefische.capstone.backend.security.controler.OAuthServerConnectionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OAuthService {

    @Value("${spring.security.oauth2.client.registration.github.clientId}")
    private String githubClientId;

    @Value("${spring.security.oauth2.client.registration.github.clientSecret}")
    private String githubClientSecret;

    @Value("${github.oauth.redirect.uri}")
    private String githubRedirectUri;

    public String getUsername(String code) {
        RestTemplate request = new RestTemplate();
        ResponseEntity<String> codePostResponse = request.postForEntity(
                "https://github.com/login/oauth/access_token?" +
                        "client_id=" + githubClientId +
                        "&redirect_uri=" + githubRedirectUri +
                        "&client_secret=" + githubClientSecret +
                        "&code=" + code,
                "",
                String.class
        );
        if (codePostResponse.getStatusCode().isError()) {
            throw new OAuthServerConnectionException("Problems connecting github oAuth, try later.");
        }
        // response looks like "access_token=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&scope=&token_type=bearer"
        String accessTokenString = codePostResponse.getBody();
        String accessToken = accessTokenString
                .split("&scope=&token_type=")[0]
                .replace("access_token=", "");

        // create headers for user info get request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+accessToken);

        // send user info get request
        ResponseEntity<String> userInfoResponse = request.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class
        );
        JsonParser parser = JsonParserFactory.getJsonParser();
        String username = parser.parseMap(userInfoResponse.getBody()).get("login").toString();
        return username;
    }
}

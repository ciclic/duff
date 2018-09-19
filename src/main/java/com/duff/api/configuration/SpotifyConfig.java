package com.duff.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Data
@Configuration
@ConfigurationProperties("spotify-api")
public class SpotifyConfig {

    String clientId;

    String clientSecret;

    String uri;

    String authUri;

    Integer searchLimit;

    String fieldsPlaylistsTracks;

    public String getAuthorizationHeader() {
        String authorization = clientId + ":" + clientSecret;
        byte[] authorizationEncoded = Base64.getEncoder().encode(authorization.getBytes());
        return "Basic " + new String(authorizationEncoded);
    }
}

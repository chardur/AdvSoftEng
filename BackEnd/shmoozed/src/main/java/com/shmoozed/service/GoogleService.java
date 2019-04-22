package com.shmoozed.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.google.api.client.json.gson.GsonFactory;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Service
public class GoogleService {

  private Logger logger = LoggerFactory.getLogger(GoogleService.class);

  private final String clientId;
  private final boolean shouldLogTokenDetails;

  private NetHttpTransport transport = new NetHttpTransport();
  private JsonFactory jsonFactory = new GsonFactory();

  public GoogleService(@Value("${google.client-id}")String clientId,
                       @Value("${google.log-token-details}")boolean shouldLogTokenDetails) {
    this.clientId = clientId;
    this.shouldLogTokenDetails = shouldLogTokenDetails;
  }

  public String validateGoogleToken(String token) {
    GoogleIdTokenVerifier tokenVerifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
      .setAudience(singletonList(clientId))
      .build();

    try {
      GoogleIdToken idToken = tokenVerifier.verify(token);
      if (idToken != null) {
        Payload payload = idToken.getPayload();
        String userId = payload.getSubject();

        if (shouldLogTokenDetails) {
          logTokenPayloadDetails(payload, userId);
        }
      } else {
        logger.error("Invalid ID token! token={}", token);
      }
    }
    catch (GeneralSecurityException e) {
      logger.error("Security Exception Occurred!", e);
    }
    catch (IOException e) {
      logger.error("IOException Occurred!", e);
    }

    // TODO: This just turns right around and returns the same token we were given. In the future this should be a new token minted by the backend.
    return token;
  }

  private void logTokenPayloadDetails(Payload payload, String userId) {
    logger.debug("User Token Information: userId={}, email={}, emailVerified={}, name={}, pictureUrl={}, " +
                   "locale={}, familyName={}, givenName={}",
                 userId, payload.getEmail(), payload.getEmailVerified(),
                 (String) payload.get("name"), (String) payload.get("picture"), (String) payload.get("locale"),
                 (String) payload.get("family_name"), (String) payload.get("given_name"));
  }

}

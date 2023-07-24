package org.mifos.connector.airtel.store;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Contains access token information.
 */
@Component
public class AccessTokenStore {
    private String accessToken;
    private LocalDateTime expiresOn;

    public AccessTokenStore() {
        this.expiresOn = LocalDateTime.now();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LocalDateTime getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(int expiresIn) {
        this.expiresOn = LocalDateTime.now().plusSeconds(expiresIn);
    }

    public boolean isValid(LocalDateTime dateTime) {
        return dateTime.isBefore(expiresOn);
    }
}

package org.mifos.connector.airtel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO representing the response body received from Airtel after authentication.
 *
 * @param accessToken Token received from Airtel.
 * @param expiresIn   Expiry time of the access token.
 * @param tokenType   Type of token.
 */
public record AuthResponseDto(
    @JsonProperty("access_token")
    String accessToken,

    @JsonProperty("expires_in")
    int expiresIn,

    @JsonProperty("token_type")
    String tokenType
) { }

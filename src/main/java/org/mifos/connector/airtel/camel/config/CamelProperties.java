package org.mifos.connector.airtel.camel.config;

/**
 * Contains properties related to camel.
 */
public class CamelProperties {

    private CamelProperties() {
    }

    public static final String CORRELATION_ID = "correlationId";
    public static final String DEPLOYED_PROCESS = "deployedProcess";
    public static final String COLLECTION_REQUEST_BODY = "collectionRequestBody";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String COLLECTION_RESPONSE_BODY = "mpesaApiResponse";
    public static final String IS_RETRY_EXCEEDED = "isRetryExceeded";
    public static final String IS_TRANSACTION_PENDING = "isTransactionPending";
    public static final String LAST_RESPONSE_BODY = "lastResponseBody";
    public static final String COUNTRY = "country";
    public static final String CURRENCY = "currency";
}

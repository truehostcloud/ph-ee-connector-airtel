package org.mifos.connector.airtel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO representing the request body received on callback.
 */
public class CallbackDto {
    private Transaction transaction;
    private String hash;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "CallbackDto{"
            + "transaction=" + transaction
            + ", hash='" + hash + '\''
            + '}';
    }

    /**
     * Contains data about the transaction.
     */
    public static class Transaction {
        private String id;
        private String message;

        @JsonProperty("status_code")
        private String statusCode;

        @JsonProperty("airtel_money_id")
        private String airtelMoneyId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getAirtelMoneyId() {
            return airtelMoneyId;
        }

        public void setAirtelMoneyId(String airtelMoneyId) {
            this.airtelMoneyId = airtelMoneyId;
        }

        @Override
        public String toString() {
            return "Transaction{"
                + "id='" + id + '\''
                + ", message='" + message + '\''
                + ", statusCode='" + statusCode + '\''
                + ", airtelMoneyId='" + airtelMoneyId + '\''
                + '}';
        }
    }
}

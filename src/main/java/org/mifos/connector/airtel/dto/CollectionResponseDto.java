package org.mifos.connector.airtel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO representing collection response body.
 */
public class CollectionResponseDto {
    private Data data;
    private Status status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Wrapper for transaction data.
     */
    public static class Data {
        private Transaction transaction;

        public Transaction getTransaction() {
            return transaction;
        }

        public void setTransaction(Transaction transaction) {
            this.transaction = transaction;
        }

        /**
         * Contains the transaction details.
         */
        public static class Transaction {
            private String id;
            private String message;
            private String status;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
                    + ", status='" + status + '\''
                    + ", airtelMoneyId='" + airtelMoneyId + '\''
                    + '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" + "transaction=" + transaction + '}';
        }
    }

    /**
     * Contains information about the transaction status.
     */
    public static class Status {
        private String code;
        private String message;

        @JsonProperty("response_code")
        private String responseCode;
        private boolean success;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        @Override
        public String toString() {
            return "Status{"
                + "code='" + code + '\''
                + ", message='" + message + '\''
                + ", responseCode='" + responseCode + '\''
                + ", success=" + success
                + '}';
        }
    }

    @Override
    public String toString() {
        return "CollectionResponseDto{"
            + "data=" + data
            + ", status=" + status
            + '}';
    }
}

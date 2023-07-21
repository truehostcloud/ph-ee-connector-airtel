package org.mifos.connector.airtel.dto;

import java.math.BigDecimal;
import java.util.Map;
import org.json.JSONObject;

/**
 * DTO representing collection request body.
 */
public class CollectionRequestDto {
    private String reference;
    private Subscriber subscriber;
    private Transaction transaction;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "CollectionRequestDto{"
            + "reference='" + reference + '\''
            + ", subscriber=" + subscriber
            + ", transaction=" + transaction
            + '}';
    }

    /**
     * Holds data about the Airtel subscriber.
     */
    public static class Subscriber {
        private String country;
        private String currency;
        private Long msisdn;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Long getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(Long msisdn) {
            this.msisdn = msisdn;
        }

        @Override
        public String toString() {
            return "Subscriber{"
                + "country='" + country + '\''
                + ", currency='" + currency + '\''
                + ", msisdn=" + msisdn
                + '}';
        }
    }

    /**
     * Holds Airtel transaction data.
     */
    public static class Transaction {
        private BigDecimal amount;
        private String country;
        private String currency;
        private String id;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Transaction{"
                + "amount=" + amount
                + ", country='" + country + '\''
                + ", currency='" + currency + '\''
                + ", id='" + id + '\''
                + '}';
        }
    }

    /**
     * Creates a {@link CollectionRequestDto} using data from the channel request body.
     *
     * @param channelRequest {@link JSONObject}
     * @param transactionId  ID of the transaction
     * @param countryCodes   a map with currency as key and country code as value
     * @return {@link CollectionRequestDto}
     */
    public static CollectionRequestDto fromChannelRequest(
        JSONObject channelRequest, String transactionId, Map<String, String> countryCodes) {
        JSONObject amountJson = channelRequest.getJSONObject("amount");
        Subscriber subscriber = new Subscriber();
        String currency = amountJson.getString("currency");
        String country = countryCodes.get(currency.toLowerCase());
        subscriber.currency = currency;
        subscriber.country = country;
        String phoneNumber = channelRequest.getJSONObject("payer")
            .getJSONObject("partyIdInfo").getString("partyIdentifier");
        // Remove country code in phone number
        subscriber.msisdn = Long.valueOf(phoneNumber.substring(4));
        Transaction transaction = new Transaction();
        transaction.amount = amountJson.getBigDecimal("amount");
        transaction.currency = currency;
        transaction.country = country;
        transaction.id = transactionId;
        CollectionRequestDto collectionRequestDto = new CollectionRequestDto();
        collectionRequestDto.setReference("Payment to OAF");
        collectionRequestDto.subscriber = subscriber;
        collectionRequestDto.transaction = transaction;
        return collectionRequestDto;
    }
}

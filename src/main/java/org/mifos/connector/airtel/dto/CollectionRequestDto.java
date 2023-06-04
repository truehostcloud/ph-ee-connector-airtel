package org.mifos.connector.airtel.dto;

import org.mifos.connector.common.channel.dto.TransactionChannelC2BRequestDTO;
import org.mifos.connector.common.gsma.dto.GsmaParty;

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

    static class Subscriber {
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

    static class Transaction {
        private Long amount;
        private String country;
        private String currency;
        private String id;

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
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
     * Creates a {@link CollectionRequestDto} using data from
     * {@link TransactionChannelC2BRequestDTO}.
     *
     * @param transactionChannelRequestDto {@link TransactionChannelC2BRequestDTO}
     * @param transactionId                ID of the transaction
     * @return {@link CollectionRequestDto}
     */
    public static CollectionRequestDto fromChannelRequest(
        TransactionChannelC2BRequestDTO transactionChannelRequestDto, String transactionId) {
        GsmaParty[] payer = transactionChannelRequestDto.getPayer();
        Subscriber subscriber = new Subscriber();
        subscriber.currency = transactionChannelRequestDto.getAmount().getCurrency();
        if (payer[0].getKey().equals("MSISDN")) {
            // case where 1st array element is MSISDN
            subscriber.msisdn = Long.parseLong(payer[0].getValue().trim());
        } else {
            // case where 1st array element is ACCOUNTID
            subscriber.msisdn = Long.parseLong(payer[1].getValue().trim());
        }
        Transaction transaction = new Transaction();
        transaction.amount = Long.parseLong(transactionChannelRequestDto.getAmount()
            .getAmount().trim());
        transaction.currency = transactionChannelRequestDto.getAmount().getCurrency();
        transaction.id = transactionId;
        CollectionRequestDto collectionRequestDto = new CollectionRequestDto();
        collectionRequestDto.setReference("Payment to OAF");
        collectionRequestDto.subscriber = subscriber;
        collectionRequestDto.transaction = transaction;
        return collectionRequestDto;
    }
}

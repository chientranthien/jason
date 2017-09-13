package com.jason.application.model;

/**
 * Created by chien on 9/13/17.
 */
public class Account {
    private Long id;
    private String name;
    private String currency;
    private Float balance;

    public Account() {
    }

    public Account(Long id, String name, String currency, Float balance) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
    }

    public Account(AccountBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.currency = builder.currency;
        this.balance = builder.balance;
    }

    public static class AccountBuilder {
        private Long id;
        private String name;
        private String currency;
        private Float balance;

        public AccountBuilder(Long id) {

            this.id = id;
        }

        public static AccountBuilder fromAccount(Account account) {
            return new AccountBuilder(account.getId())
                    .currency(account.getCurrency())
                    .balance(account.getBalance())
                    .name(account.getName());
        }

        public AccountBuilder name(String name) {
            if (name != null)
                this.name = name;
            return this;
        }

        public AccountBuilder currency(String currency) {
            if (currency != null)
                this.currency = currency;
            return this;
        }

        public AccountBuilder id(Long id) {
            if (id != null)
                this.id = id;
            return this;
        }

        public AccountBuilder balance(Float balance) {
            if (balance != null)
                this.balance = balance;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}

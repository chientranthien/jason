package com.jason.application.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.application.model.Account;
import com.oracle.webservices.internal.api.databinding.Databinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by chien on 9/13/17.
 */
@Component
public class AccountRepository {
    @Autowired
    ApplicationContext applicationContext;

    @Value("${data.path}")
    String path;

    List<Account> users;

    @PostConstruct
    void load() throws IOException {
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(is, new TypeReference<List<Account>>() {
        });

    }

    public Account get(Long id) {
        return users.stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }

    public List<Account> getAll() {
        return users;
    }

    public Account update(Long id, Account account) {
        users = users.stream()
                .map(a -> {
                    if (a.getId() == id) {
                        Account.AccountBuilder accountBuilder = Account.AccountBuilder.fromAccount(a);
                        accountBuilder
                                .balance(account.getBalance())
                                .currency(account.getCurrency())
                                .name(account.getName());
                        return accountBuilder.build();
                    }
                    return a;
                }).collect(Collectors.toList());
        write();
        return users.stream().filter(a -> a.getId() == id).findFirst().orElse(null);

    }

    public Account create(Account account) {
        if (account.getId() != null)
            users.add(account);
        else {
            Long id = users.stream().max((a1, a2) -> Long.compare(a1.getId(), a2.getId())
            ).map(a -> a.getId()).get();
            account.setId(id + 1);
            users.add(account);

        }
        write();
        return account;
    }

    private void write() {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            OutputStream outputStream = new FileOutputStream(path);
            mapper.writeValue(outputStream, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

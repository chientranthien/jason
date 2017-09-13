package com.jason.application.controller;

import com.jason.application.model.Account;
import com.jason.application.rs.PATCH;
import com.jason.application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by chien on 9/13/17.
 */

@RestController
@Path("/v1/accounts/account")
public class AccountController {


    @Autowired
    AccountRepository accountRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account get(@PathParam("id") Long id) throws IOException {
        return accountRepository.get(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAll() {

        return accountRepository.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account create(@RequestBody Account account) {

        return accountRepository.create(account);
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account update(@PathParam("id") Long id, @RequestBody Account account) {
        return accountRepository.update(id,account);
    }

}

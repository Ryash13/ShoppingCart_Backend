package com.shoppingzone;

import com.shoppingzone.pojo.Ewallet;
import com.shoppingzone.pojo.Statement;
import com.shoppingzone.repository.EwalletRepository;
import com.shoppingzone.service.EwalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WalletServiceLayerTesting {

    @Autowired
    private EwalletService service;

    @MockBean
    private EwalletRepository repository;

    // Testing for adding a new wallet to database
    @Test
    public void checkForAddOfNewWallet() {
        Ewallet wallet = new Ewallet(100, 20.0);
        when(service.addWallet(wallet)).thenReturn(wallet);
        assertEquals(20.0, service.addWallet(wallet).getCurrentBalance(), 0);
    }

    // Testing the number of wallets available in the database
    @Test
    public void checkForListOfWallets() {
        List<Ewallet> wallets = new ArrayList<Ewallet>();
        Ewallet wallet1 = new Ewallet(1, 10.0);
        Ewallet wallet2 = new Ewallet(2, 20.0);
        wallets.add(wallet1);
        wallets.add(wallet2);
        when(service.getWallets()).thenReturn(wallets);
        assertEquals(2, service.getWallets().size());
    }

    // Testing the size of statements available in database(will fail as the statement generates)
    @Test
    public void checkForGetStatementsSize() {
        List<Statement> statements = service.getStatements();
        assertEquals(1, statements.size());
    }
}

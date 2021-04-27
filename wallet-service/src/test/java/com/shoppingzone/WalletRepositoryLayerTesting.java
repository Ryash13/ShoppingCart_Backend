package com.shoppingzone;

import com.shoppingzone.pojo.Ewallet;
import com.shoppingzone.repository.EwalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WalletRepositoryLayerTesting {

    @Autowired
    private EwalletRepository repository;

    @Test
    public void findAllWalletTest() {
        List<Ewallet> ewalletList = repository.findAll();
        assertFalse(ewalletList.isEmpty());
    }

    @Test
    public void findByWalletIdTest() {
        Optional<Ewallet> ewallet = repository.findById(1);
        assertEquals(1 , ewallet.get().getWalletId());
    }
}

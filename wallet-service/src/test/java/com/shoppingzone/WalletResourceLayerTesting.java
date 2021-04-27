package com.shoppingzone;

import com.shoppingzone.pojo.Ewallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WalletResourceLayerTesting {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void toCheckGetWalletSuccessfully() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/wallets", String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void toCheckGetWalletFail() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/wallet", String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void toCheckGetByWalletIdSuccessfully() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/wallets/1", String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void toCheckGetByWalletIdFail() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/wallets/10", String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void toCheckAddWalletSuccess() {
        Ewallet wallet = new Ewallet(4, 100.0);
        ResponseEntity<String> entity = restTemplate.postForEntity("/wallets/" + wallet.getWalletId(), wallet, String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void toCheckAddWalletFailure() {
        Ewallet wallet = new Ewallet(1, 100.0);
        ResponseEntity<String> entity = restTemplate.postForEntity("/wallet", wallet, String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void toCheckGetStatementsSuccess() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/wallets/statements", String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void toCheckGetStatementsFailure() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/wallets/statement", String.class);
        assertEquals(entity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

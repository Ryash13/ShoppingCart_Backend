package com.shoppingzone.service;

import com.shoppingzone.exception.ResourceNotFoundException;
import com.shoppingzone.pojo.Ewallet;
import com.shoppingzone.pojo.Statement;
import com.shoppingzone.repository.EwalletRepository;
import com.shoppingzone.repository.StatementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EwalletServiceImpl implements EwalletService {

    private static int statementId = 1;

    @Autowired
    EwalletRepository ewalletRepository;

    @Autowired
    StatementsRepository statementsRepository;

    @Override
    public List<Ewallet> getWallets() {
        return ewalletRepository.findAll();
    }

    @Override
    public Ewallet addWallet(Ewallet wallet) {
        return ewalletRepository.save(wallet);
    }

    @Override
    public void addmoney(Ewallet wallet, double amount, String transactionType)
    {
        ewalletRepository.save(wallet);
    }

    @Override
    public void update(Ewallet wallet, double amount, String transactionType,int orderId) {
        ewalletRepository.save(wallet);
        Statement statement = new Statement();
        statement.setAmount(amount);
        statement.setStatementId(statementId++);
        statement.setDateTime(LocalDateTime.now());
        statement.setOrderId(orderId);
        statement.setTransactionRemarks("done");
        statement.setTransactionType(transactionType);
        statement.setEwallet(wallet);
        statementsRepository.save(statement);
    }

    @Override
    public Ewallet getById(int walletId) {
        Optional<Ewallet> wallet = ewalletRepository.findById(walletId);
        if (wallet.isPresent()) {
            return wallet.get();
        } else
            throw new ResourceNotFoundException("Walled does not exists with ID:- " + walletId);
    }

    @Override
    public List<Statement> getStatementsById(int walletId) {
        Ewallet wallets = ewalletRepository.findById(walletId).orElseThrow(() ->
                new ResourceNotFoundException("No Statements available for this walletId:- " + walletId));
        return wallets.getStatements();

    }

    @Override
    public List<Statement> getStatements() {
        return statementsRepository.findAll();
    }

    @Override
    public void deleteByid(int id) {
        Ewallet search = ewalletRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to delete wallet of ID:- " + id + " because No wallet " +
                        "exists with ID:- " + id));
        ewalletRepository.deleteById(id);
    }
}

package com.shoppingzone.service;

import com.shoppingzone.pojo.Ewallet;
import com.shoppingzone.pojo.Statement;

import java.util.List;

public interface EwalletService {

    public List<Ewallet> getWallets();
    Ewallet addWallet(Ewallet wallet);
    public void addmoney(Ewallet wallet, double amount, String transactionType);
    public void update(Ewallet wallet, double amount, String transactionType,int orderid);
    public Ewallet getById(int walletId);
    public List<Statement> getStatementsById(int walletId);
    public List<Statement> getStatements();
    public void deleteByid(int id);
}

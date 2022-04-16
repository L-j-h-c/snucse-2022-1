package bank;

import bank.event.*;
import security.*;
import security.key.*;

public class Bank {
    private int numAccounts = 0;
    final static int maxAccounts = 100;
    private BankAccount[] accounts = new BankAccount[maxAccounts];
    private String[] ids = new String[maxAccounts];

    public void createAccount(String id, String password) {
        //TODO: Problem 1.1
        for(int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)) return;
        }

        accounts[numAccounts] = new BankAccount(id, password, 0);
        ids[numAccounts++] = id;
    }

    public void createAccount(String id, String password, int initBalance) {
        //TODO: Problem 1.1
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)) return;
        }

        accounts[numAccounts] = new BankAccount(id, password, initBalance);
        ids[numAccounts++] = id;
    }

    public boolean deposit(String id, String password, int amount, int transId) {
        //TODO: Problem 1.1
        for (int i = 0; i < numAccounts; i++) {
            // id가 존재하고, 그 id와 password가 일치하면
            if(ids[i].equals(id)) {
                if (accounts[i].authenticate(password)) {
                    accounts[i].deposit(amount, transId);
                    return true;
                } else return false;
            }
        }
        return false;
    }

    public boolean withdraw(String id, String password, int amount, int transId) {
        //TODO: Problem 1.1
        for (int i = 0; i < numAccounts; i++) {
            // id가 존재하고, 그 id와 password가 일치하면
            if(ids[i].equals(id)) {
                if (accounts[i].authenticate(password)) {
                    return accounts[i].withdraw(amount, transId);
                } else return false;
            }
        }
        return false;
    }

    public boolean transfer(String sourceId, String password, String targetId, int amount, int transId) {
        //TODO: Problem 1.1
        boolean checkId = false;
        int targetIndex = 0;
        for (int j = 0; j < numAccounts; j++) {
            if(ids[j].equals(targetId)) {
                checkId = true;
                targetIndex = j;
            }
        }
        if(!checkId) return false;

        for (int i = 0; i < numAccounts; i++) {
            // id가 존재하고, 그 id와 password가 일치하면
            if(ids[i].equals(sourceId)) {
                if (accounts[i].authenticate(password)) {
                    if(accounts[i].send(amount, transId)) {
                        accounts[targetIndex].receive(amount, transId);
                        return true;
                    }
                } else return false;
            }
        }
        return false;
    }

    public Event[] getEvents(String id, String password) {
        //TODO: Problem 1.1
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)) {
                if (accounts[i].authenticate(password)) return accounts[i].getEvents();
            }
        }
        return null;
    }

    public int getBalance(String id, String password) {
        //TODO: Problem 1.1
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)) {
                if (accounts[i].authenticate(password)) return accounts[i].getBalance();
            }
        }
        return -1;
    }

    private static String randomUniqueStringGen(){
        return Encryptor.randomUniqueStringGen();
    }
    private BankAccount find(String id) {
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)){return accounts[i];}
        }
        return null;
    }


    private BankSecretKey secretKey;
    public BankPublicKey getPublicKey(){
        BankKeyPair keypair = Encryptor.publicKeyGen(); // generates two keys : BankPublicKey, BankSecretKey
        secretKey = keypair.deckey; // stores BankSecretKey internally
        return keypair.enckey;
    }

    int maxHandshakes = 10000;
    int numSymmetrickeys = 0;
    BankSymmetricKey[] bankSymmetricKeys = new BankSymmetricKey[maxHandshakes];
    String[] AppIds = new String[maxHandshakes];

    public int getAppIdIndex(String AppId){
        for(int i=0; i<numSymmetrickeys; i++){
            if(AppIds[i].equals(AppId)){
                return i;
            }
        }
        return -1;
    }

//    public void fetchSymKey(Encrypted<BankSymmetricKey> encryptedKey, String AppId){
//        //TODO: Problem 1.2
//    }
//
//    public Encrypted<Boolean> processRequest(Encrypted<Message> messageEnc, String AppId) {
//        //TODO: Problem 1.2
//    }
//
//    public void createAccount(String id, String password, int initBalance, String question, String answer) {
//        //TODO: Problem 1.3
//    }
//
//    public boolean compensate(String id, String password, String questionAnswer, int[] transIdList){
//        //TODO: Problem 1.3
//    }
}

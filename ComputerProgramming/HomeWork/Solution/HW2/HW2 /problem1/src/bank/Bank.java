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

    // bankPublicKey와 bankSecretKey의 세트를 만든다. bankPublicKey로 encrypt된 것은 bankSecretKey로만 해제될 수 있다.
    private BankSecretKey secretKey;
    public BankPublicKey getPublicKey(){
        BankKeyPair keypair = Encryptor.publicKeyGen(); // generates two keys : BankPublicKey, BankSecretKey
        secretKey = keypair.deckey; // stores BankSecretKey internally
        return keypair.enckey;
    }

    int maxHandshakes = 10000;
    int numSymmetrickeys = 0;

    // bankSymmetricKeys를 저장할 배열. mobileApp과 handShake할때마다 저장.
    // bankSymmetricKey는 AppId와 페어를 이뤄서 저장되어야 한다.
    // encrtyptedKey가 비었거나, decryption이 실패할 경우 아무것도 저장하지 않는다.
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

    // 디버깅 : fetchSymKey까지는 문제가 없었다.
    public void fetchSymKey(Encrypted<BankSymmetricKey> encryptedKey, String AppId){
        //TODO: Problem 1.2
       BankSymmetricKey tmpKey = encryptedKey.decrypt(secretKey);
       if(tmpKey!=null) {
           if(getAppIdIndex(AppId)!=-1) {
               bankSymmetricKeys[getAppIdIndex(AppId)] = tmpKey;
           } else {
               bankSymmetricKeys[numSymmetrickeys] = tmpKey;
               AppIds[numSymmetrickeys] = AppId;
               numSymmetrickeys++;
           }
       }
    }

    public Encrypted<Boolean> processRequest(Encrypted<Message> messageEnc, String AppId) {
        //TODO: Problem 1.2
        BankSymmetricKey symKey = bankSymmetricKeys[getAppIdIndex(AppId)];
        if(symKey == null) return null;
        if(messageEnc.decrypt(symKey)==null) {
            return null;
        }
        else {
            String requestType = messageEnc.decrypt(symKey).getRequestType();
            String id = messageEnc.decrypt(symKey).getId();
            String password = messageEnc.decrypt(symKey).getPassword();
            int amount = messageEnc.decrypt(symKey).getAmount();
            int transId = messageEnc.decrypt(symKey).getTransId();
            if(requestType.equals("deposit")) {
                return new Encrypted<Boolean>(this.deposit(id, password, amount, transId), symKey);
            } else if (requestType.equals("withdraw")) {
                return new Encrypted<Boolean>(this.withdraw(id, password, amount, transId), symKey);
            } else {
                String questionAnswer = messageEnc.decrypt(symKey).getQnA();
                int[] transIds = messageEnc.decrypt(symKey).getTransIdList();
                return new Encrypted<Boolean>(this.compensate(id, password, questionAnswer, transIds), symKey);
            }
        }
    }

    public void createAccount(String id, String password, int initBalance, String question, String answer) {
        //TODO: Problem 1.3

        // 동일한 id가 존재할 경우 answer와 question 수정
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)) {
                if (accounts[i].authenticate(password)) {
                    accounts[i].setQuestion(question);
                    accounts[i].setAnswer(answer);
                    return;
                } else {
                    return;
                }
            }
        }

        // 아래는 새로운 계정일 경우임
        accounts[numAccounts] = new BankAccount(id, password, initBalance, question, answer);
        ids[numAccounts++] = id;
    }

    public boolean compensate(String id, String password, String questionAnswer, int[] transIdList){
        //TODO: Problem 1.3
        for (int i = 0; i < numAccounts; i++) {
            if(ids[i].equals(id)) {
                if (accounts[i].authenticate(password) && accounts[i].secondaryAuthenticate(questionAnswer)) {
                    Event[] events = accounts[i].getEvents();
                    int sum=0;

                    for(int j=0; j<transIdList.length; j++) {
                        for(Event event : events) {
                            if(event.getTransId() == transIdList[j] && event.toCustomString() == "WITHDRAW") {
                                sum += event.getAmount();
                            }
                        }
                    }
                    accounts[i].compensate(sum);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}

package bank;

import bank.event.*;

class BankAccount {
    // Do NOT change access modifier
    private Event[] events = new Event[maxEvents];
    final static int maxEvents = 100;
    private String id;
    private String password;
    private int balance;
    private String question = null;
    private String answer = null;

    private int eventIndex = 0;

    BankAccount(String id, String password, int balance) {
        //TODO: Problem 1.1
        this.id = id;
        this.password = password;
        this.balance = balance;
    }

    BankAccount(String id, String password, int balance, String question, String answer) {
        //TODO: Problem 1.3
    }


    boolean authenticate(String password) {
        //TODO: Problem 1.1
        if (this.password.equals(password)) return true;
        else return false;
    }

    void deposit(int amount, int transId) {
        //TODO: Problem 1.1
        this.balance += amount;
        this.events[eventIndex++] = new DepositEvent(amount, transId);
    }

    boolean withdraw(int amount, int transId) {
        //TODO: Problem 1.1
        if(this.balance>=amount) {
            this.balance -= amount;
            this.events[eventIndex++] = new WithdrawEvent(amount, transId);
            return true;
        } else {
            return false;
        }
    }

    void receive(int amount, int transId) {
        //TODO: Problem 1.1
        this.balance += amount;
        this.events[eventIndex++] = new ReceiveEvent(amount, transId);
    }

    boolean send(int amount, int transId) {
        //TODO: Problem 1.1
        if(this.balance>=amount) {
            this.balance -= amount;
            this.events[eventIndex++] = new SendEvent(amount, transId);
            return true;
        } else {
            return false;
        }
    }

    boolean secondaryAuthenticate(String questionAnswer) {
        //TODO: Problem 1.3
    }
}
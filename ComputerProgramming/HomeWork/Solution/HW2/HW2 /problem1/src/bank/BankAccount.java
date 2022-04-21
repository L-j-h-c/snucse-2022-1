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
        this.id = id;
        this.password = password;
        this.balance = balance;
        this.question = question;
        this.answer = answer;
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

    public Event[] getEvents() {
        if(eventIndex==0&&events[0]==null) return null;
        Event[] eventArray = new Event[eventIndex];
        for(int i=0; i<eventIndex; i++) {
                eventArray[i] = events[i];
        }
        return eventArray;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    boolean secondaryAuthenticate(String questionAnswer) {
        //TODO: Problem 1.3
        String[] terms = questionAnswer.split(",");
        if(this.question.equals(terms[0]) && this.answer.equals(terms[1])) return true;
        else return false;
    }

    public void compensate(int amount) {
        this.balance += amount;
    }
}
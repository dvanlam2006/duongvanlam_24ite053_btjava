package oop6;

public class Account {
    private String id;
    private String name;
    private int balance = 0;
    public Account() {}
    public Account(String id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }
    public int Credit(int amount) {
         return balance + amount;
    }
    public int Debit(int amount) {
        if (amount <= balance) {
            balance += amount;
        }
        else{
            System.out.println("Amount Exceeded Balance");
        }
        return balance;
    }
    public int transfer(int amount, Account another ) {
        if (amount <= balance) {
            balance -= amount;
        }
        else{
            System.out.println("Amount Exceeded Balance");
        }
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

package org.example.model;


public class Loans {

    private int id;
    private int userId;
    private String loanType;
    private int  amount;
    private String status;

    public Loans(){

    }

    public Loans(int id, int userId, String loanType, int amount, String status) {
        this.id = id;
        this.userId = userId;
        this.loanType = loanType;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "id=" + id +
                ", userId=" + userId +
                ", loanType='" + loanType + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}


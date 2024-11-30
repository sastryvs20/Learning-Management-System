package com.cme.entity.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeBankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueAccountID;

    private String accountNumber;  // Account number as primary key
    private String holderName;
    private String bankName;
    private String branchName;
    private String ifscCode;
    private String accountType;

    // Getters and Setters

    public Long getUniqueAccountID() {
        return uniqueAccountID;
    }

    public void setUniqueAccountID(Long uniqueAccountID) {
        this.uniqueAccountID = uniqueAccountID;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getHolderName()
    {
        return holderName;
    }

    public void setHolderName(String holderName)
    {
        this.holderName = holderName;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBranchName()
    {
        return branchName;
    }

    public void setBranchName(String branchName)
    {
        this.branchName = branchName;
    }

    public String getIfscCode()
    {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode)
    {
        this.ifscCode = ifscCode;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }
}

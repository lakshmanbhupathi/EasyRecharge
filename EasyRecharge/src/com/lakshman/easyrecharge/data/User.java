package com.lakshman.easyrecharge.data;

import com.lakshman.easyrecharge.enums.TariffPlan;

import java.math.BigDecimal;
import java.util.Objects;

public class User {
    private Long mobileNumber;
    private BigDecimal balance;
    private TariffPlan plan;

    public User(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(mobileNumber, user.mobileNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mobileNumber);
    }

    public TariffPlan getPlan() {
        return plan;
    }

    public void setPlan(TariffPlan plan) {
        this.plan = plan;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

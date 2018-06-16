package com.lakshman.easyrecharge.service;

import com.lakshman.easyrecharge.enums.TariffPlan;

import java.math.BigDecimal;

public interface EasyRecharge {
    boolean isUserExists(Long mobileNumber);

    void createNewUser(Long mobileNumber);

    void generateOTP(Long mobileNumber);

    boolean validateOtp(Long mobileNumber, String otpInput);

    BigDecimal getBalanceByMobileNumber(Long mobileNumber);

    void updateTariffPlan(TariffPlan tariffPlan, Long mobileNumber);

    void rechargeBalance(Long mobileNumber, int amount);
}

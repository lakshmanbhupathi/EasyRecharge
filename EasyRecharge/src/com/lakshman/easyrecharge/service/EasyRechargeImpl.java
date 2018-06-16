package com.lakshman.easyrecharge.service;

import com.lakshman.easyrecharge.data.User;
import com.lakshman.easyrecharge.enums.TariffPlan;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EasyRechargeImpl implements EasyRecharge{
    private final Map<Long, User> usersMap;
    private final Map<Long, Integer> unRegisteredUsersMap;
    private Random random;

    public EasyRechargeImpl() {
        usersMap = new HashMap<>();
        unRegisteredUsersMap = new HashMap<>();
        random = new Random();
    }

    @Override
    public boolean isUserExists(final Long mobileNumber) {
        return usersMap.containsKey(mobileNumber);
    }

    @Override
    public void createNewUser(final Long mobileNumber) {
        final User newUser = new User(mobileNumber);
        newUser.setBalance(new BigDecimal(100));
        newUser.setPlan(TariffPlan.PLAN_1RS);

        usersMap.put(mobileNumber, newUser);
    }

    @Override
    public void generateOTP(Long mobileNumber) {
        // generate value between 1000 to 9999 four digit number
        int otp = random.nextInt(8999) + 1000;

        // for testing purpose
        System.out.println("Generated OTP :: " + otp);

        unRegisteredUsersMap.put(mobileNumber, otp);
    }

    @Override
    public boolean validateOtp(Long mobileNumber, String otpInput) {
        boolean result = false;

        try {
            if (unRegisteredUsersMap.containsKey(mobileNumber)) {
                result = unRegisteredUsersMap.get(mobileNumber).equals(Integer.parseInt(otpInput));
            }
        } catch (NumberFormatException ne) {
            result = false;
        }

        return result;
    }


    @Override
    public BigDecimal getBalanceByMobileNumber(Long mobileNumber) {
        return usersMap.get(mobileNumber).getBalance();
    }

    @Override
    public void updateTariffPlan(TariffPlan tariffPlan, Long mobileNumber) {
        usersMap.get(mobileNumber).setPlan(tariffPlan);
    }

    @Override
    public void rechargeBalance(Long mobileNumber, int amount) {
        User user = usersMap.get(mobileNumber);
        user.setBalance(user.getBalance().add(new BigDecimal(amount)));
    }
}

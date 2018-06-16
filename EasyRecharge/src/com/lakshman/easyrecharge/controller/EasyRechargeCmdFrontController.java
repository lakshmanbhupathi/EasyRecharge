package com.lakshman.easyrecharge.controller;

import com.lakshman.easyrecharge.enums.TariffPlan;
import com.lakshman.easyrecharge.service.EasyRecharge;
import com.lakshman.easyrecharge.service.EasyRechargeImpl;
import com.lakshman.easyrecharge.util.ValidationUtil;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.lakshman.easyrecharge.constant.EasyRechargeConstants.BALANCE_THRESHOLD;
import static com.lakshman.easyrecharge.constant.EasyRechargeConstants.INVALID_INPUT;
import static com.lakshman.easyrecharge.constant.EasyRechargeConstants.RECHARGE_SUCCESSFULLY;

public class EasyRechargeCmdFrontController implements EasyRechargeController {
    private final Scanner scanner;
    private final EasyRecharge easyRecharge;

    public EasyRechargeCmdFrontController() {
        scanner =new Scanner(System.in);
        easyRecharge = new EasyRechargeImpl();
    }

    @Override
    public void startEasyRechargeJourney() {
        System.out.println("Welcome to Easy Recharge. Please enter your phone number to continue :: ");
        String input = scanner.nextLine();

        if (ValidationUtil.isValidMobileNumber(input)) {

            Long mobileNumber = Long.parseLong(input);
            if (easyRecharge.isUserExists(mobileNumber)) {
                successfulAuthenticationJourney(mobileNumber);

            } else {
                generateAndValidateOtp(mobileNumber);

            }
        } else {
            System.err.println("Invalid Mobile Number..!!!");
            startEasyRechargeJourney();

        }
    }

    private void successfulAuthenticationJourney(final Long mobileNumber) {
        System.out.println("What would you like to do?");
        System.out.println("Press 1 to Check Balance");
        System.out.println("Press 2 Recharge");
        int menuInput = scanner.nextInt();
        switch (menuInput) {
            case 1:
                BigDecimal balance = easyRecharge.getBalanceByMobileNumber(mobileNumber);
                System.out.println("Your balance is " + balance + " Rs.");

                rechargeConfirmationJourney(mobileNumber, balance);
                break;

            case 2:
                rechargeJourney(mobileNumber);
                break;

            default:
                System.err.println(INVALID_INPUT);
                successfulAuthenticationJourney(mobileNumber);
                break;
        }
    }

    private void rechargeConfirmationJourney(final Long mobileNumber, final BigDecimal balance) {


        if (balance.compareTo(BALANCE_THRESHOLD) < 1) {
            System.out.println("Would you like to recharge?");
            System.out.println("Yes to Proceed to recharge menu");
            System.out.println("No to exit");

            String subMenuInput = scanner.next();
            switch (subMenuInput.toUpperCase()) {
                case "YES":
                    rechargeJourney(mobileNumber);
                    break;

                case "NO":
                    System.exit(0);

                default:
                    System.err.println(INVALID_INPUT);
                    rechargeConfirmationJourney(mobileNumber, balance);
            }
        }
    }

    private void rechargeJourney(final Long mobileNumber) {
        System.out.println("We have following bundles you can use, choose one\n" +
                "                        1. 50p for 1 minute call. 400 Rs per month\n" +
                "                        2. 25p for 1 minute call. 600 Rs per month\n" +
                "                        3. 1Rs for 1 minute call. ");

        int menuInput = scanner.nextInt();
        switch (menuInput) {
            case 1:
                easyRecharge.updateTariffPlan(TariffPlan.PLAN_50P, mobileNumber);
                System.out.println(RECHARGE_SUCCESSFULLY);
                break;
            case 2:
                easyRecharge.updateTariffPlan(TariffPlan.PLAN_25P, mobileNumber);
                System.out.println(RECHARGE_SUCCESSFULLY);
                break;
            case 3:
                rechargeArbitraryJourney(mobileNumber);
                break;
            default:
                System.err.println(INVALID_INPUT);
                rechargeJourney(mobileNumber);
                break;
        }

    }

    private void rechargeArbitraryJourney(final Long mobileNumber) {
        System.out.println("Please enter amount to recharge :: ");
        int amount = scanner.nextInt();
        if (ValidationUtil.isValidRechargeAmount(amount)) {
            easyRecharge.updateTariffPlan(TariffPlan.PLAN_1RS, mobileNumber);
            easyRecharge.rechargeBalance(mobileNumber, amount);
            System.out.println(RECHARGE_SUCCESSFULLY);
        } else {
            System.err.println("You can not recharge with a specific amount. Please re-enter the amount.");
            rechargeArbitraryJourney(mobileNumber);
        }
    }

    private void generateAndValidateOtp(final Long mobileNumber) {
        easyRecharge.generateOTP(mobileNumber);
        validateOtp(mobileNumber);
    }

    private void validateOtp(final Long mobileNumber) {
        System.out.println("Please Enter your OTP sent to you :: ");
        String otpInput = "";
        if (scanner.hasNext()) {
            otpInput = scanner.next();
        }

        if (easyRecharge.validateOtp(mobileNumber, otpInput)) {
            easyRecharge.createNewUser(mobileNumber);
            successfulAuthenticationJourney(mobileNumber);

        } else {
            invalidOtpJourney(mobileNumber);

        }
    }

    private void invalidOtpJourney(final Long mobileNumber) {
        try {
            System.err.println("Your OTP is invalid! Press 1 to re-enter, Press 2 to re-generate");
            final int menuInput = scanner.nextInt();
            switch (menuInput) {
                case 1:
                    validateOtp(mobileNumber);
                    break;

                case 2:
                    generateAndValidateOtp(mobileNumber);
                    break;

                default:
                    System.err.println(INVALID_INPUT);
                    validateOtp(mobileNumber);
                    break;
            }

        } catch (InputMismatchException inputMismatchExp) {
            invalidOtpJourney(mobileNumber);
        }
    }
}
package com.lakshman.easyrecharge;

import com.lakshman.easyrecharge.controller.EasyRechargeCmdFrontController;
import com.lakshman.easyrecharge.controller.EasyRechargeController;

public class Test {
    public static void main(String args[]) {
        EasyRechargeController cmdFrontController = new EasyRechargeCmdFrontController();
        cmdFrontController.startEasyRechargeJourney();
    }
}

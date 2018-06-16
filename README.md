# EasyRecharge
Easy Recharge Application : Command line application

Problem Statement:

```javascript
Create a Configuration based question answering system for user interaction(close-ended).
We would like to collect information from the user through mobile phones in different stages of the customer
journey. The customer journey may change frequently or may be assigned depending upon the acquisition
channel. Thus we need a unified system that can capture user answers based on the configuration it has.
The types of questions are
● Optional
● Text fields
● Yes or No
The optional questions and Yes/No question will decide what question will be asked next. The text fields
question will be used later to store the user information. The text fields may have validation.
User Journey
Questions For existing User
A. Q: What would you like to do?
            a. A: Press 1 to Check Balance
                    i. Msg: Your balance is 100 Rs.
                        1. If the balance is < 200 then
                                a. Would you like to recharge?
                                        i. A: Yes --> take user to recharge menu.
                                        ii. A: No to exit

                        2. Else (if the balance is > 200) Press OK to exit

            b. A: Press 2 Recharge
                   i. We have following bundles you can use, choose one
                        1. 50p for 1 minute call. 400 Rs per month
                        2. 25p for 1 minute call. 600 Rs per month
                        3. 1Rs for 1 minute call. Recharge arbitrary money
                            a. A: Text Field (Validate for recharge amount <0 and >10000)
                                i. Valid case: Recharge successfully
                                ii. Invalid case: You can not recharge with a specific amount. Please re-enter the amount.

Questions For New User
1. Q: Welcome to Easy Recharge. Please enter your phone number to continue.
            a. A: Text Field: (Basic mobile number validation)
                   i. Success: Register customer and continue; Generate and save the OTP in memory
                   ii. Failure: Show error and display the same question

2. Q: Please Enter your OTP sent to you
            a. A: Text field: (4 digit number)
                    i. Success: Load existing customer question config and show those question.
                    ii. Failure: Your OTP is invalid! Press 1 to re-enter, Press 2 to re-generate
                            1. A: 1, take to previous enter your OTP flow
                            2. A: 2, take to generate OTP flow and show the text field
                            
```

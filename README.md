## About The Project

In the data base you start with 2 Account Holder, 1 Admin and 1 ThirdParty created.
But if you decide you can create new one, always form the Admin.

Each tipa of User has a different set of permissions:

1. Admin can create new accounts and modify the balance of existing accounts.
2. Account Holder can transfer funds from their accounts to other users' accounts, regardless of the owner of the target account, but as long as the account balance allows.
3. Third parties can fund and debit owners' accounts, provided they provide the correct set of data, including the hashKey, in the transaction.

There are 4 types of account an Admin can create, and Account Holder use.

1. Checking
2. StudentChecking
3. CreditCard
4. Saving

The more basic one is the StudentChecking with the next attributes:
- balance
- secretKey
- PrimaryOwner
- optional SecondaryOwner
- creationDate
- status (FROZEN, ACTIVE)

If the primaryOwner is over 24, a Checking account must create. The diference between both accounts is that Checking have these attributes mora:
- minimumBalance of 250
- penaltyFee of 40
- monthlyMaintenanceFee of 12

Savings are similar to Checking except for:
- Do NOT have a monthlyMaintenanceFee
- Do have an interestRate with a default of 0.0025.
- may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5
- minimum Balance of 1000
- may be instantiated with a minimum balance of less than 1000 but no lower than 100

CreditCard Accounts have:

- balance
- PrimaryOwner
- optional SecondaryOwner
- creditLimit with a default 100, and max 100 000
- interestRate with a default 0.2, min 0.1
- penaltyFee of 40

General rules

If any account drops below the minimumBalance, the penaltyFee is deducted from the balance automatically, but only once in a given month.
Interest on savings accounts is added to the account annually at the rate of specified interestRate per year.
Interest on credit cards is added to the balance monthly. When the balance of a credit card is accessed, application adds transactions for each month since account creation date or last interest rates accrual. Interest is charged on the difference between the credit limit and the account balance. In other words, interest is charged on the funds used.
The application recognizes patterns that indicate fraud and freeze the account status when potential fraud is detected. Patterns that indicate fraud include:

Transactions made in 24 hours total to more than 150% of the customers highest daily total transactions in any other 24 hour period.
More than 2 transactions occurring on a single account within a 1 second period.

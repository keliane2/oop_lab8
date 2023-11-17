package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestStrictBankAccount {

    private final static int INITIAL_AMOUNT = 100;

    // 1. Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi=new AccountHolder("Mario", "Rossi", 01);
    private BankAccount bankAccount=new StrictBankAccount(mRossi, INITIAL_AMOUNT);

    @BeforeEach
    public void setUp() {
        AccountHolder mRossi=new AccountHolder("Mario", "Rossi", 01);
        BankAccount bankAccount=new StrictBankAccount(mRossi, INITIAL_AMOUNT);
    }

    // 2. Test the initial state of the StrictBankAccount
    @Test
    public void testInitialization() {
        assertEquals(this.mRossi, this.bankAccount.getAccountHolder());
        assertEquals(INITIAL_AMOUNT, this.bankAccount.getBalance());
        assertEquals(0, this.bankAccount.getTransactionsCount());
        //fail("Bad initial state");
    }


    // 3. Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
    @Test
    public void testManagementFees() {
        this.bankAccount.deposit(mRossi.getUserID(), 100);
        this.bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(0, this.bankAccount.getTransactionsCount());
        assertEquals(this.bankAccount.getBalance(), INITIAL_AMOUNT+100-StrictBankAccount.MANAGEMENT_FEE-StrictBankAccount.TRANSACTION_FEE);
        //fail("TestManagementFees failed");
    }

    // 4. Test the withdraw of a negative value
    @Test
    public void testNegativeWithdraw() {
        final int NEGATIVE_VALUE=-15;
        try {
            this.bankAccount.withdraw(mRossi.getUserID(), NEGATIVE_VALUE);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot withdraw a negative amount",e.getMessage());
        }
    }

    // 5. Test withdrawing more money than it is in the account
    @Test
    public void testWithdrawingTooMuch() {
        try {
            this.bankAccount.withdraw(mRossi.getUserID(), this.bankAccount.getBalance()*2);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Insufficient balance", e.getMessage());
        }
    }
}

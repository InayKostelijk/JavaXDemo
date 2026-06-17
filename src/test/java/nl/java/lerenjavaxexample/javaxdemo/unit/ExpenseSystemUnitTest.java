package nl.java.lerenjavaxexample.javaxdemo.unit;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.Expense;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.ExpenseSystem;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.enums.ExpenseCategory;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpenseSystemUnitTest {

    @Test
    public void shouldCreateExpense() {
        ExpenseSystem system = new ExpenseSystem();
        User user = new User();

        Expense expense = new Expense(
                "medicine",
                ExpenseCategory.HEALTH,
                10.0,
                user
        );

        Expense result = system.createExpense(expense);

        assertEquals(1, system.getExpenseList().size());
        assertEquals(expense, result);
        assertEquals(expense, system.getExpenseList().get(0));
    }

    @Test
    public void shouldCreateUser() {
        ExpenseSystem system = new ExpenseSystem();
        User user = new User();

        User result = system.createUser(user);

        assertEquals(1, system.getUserList().size());
        assertEquals(user, result);
        assertEquals(user, system.getUserList().get(0));
    }

    @Test
    public void shouldReturnId() {
        ExpenseSystem system = new ExpenseSystem();

        assertEquals("ExpenseSystem", system.getId());
    }

    @Test
    public void shouldInitializeEmptyLists() {
        ExpenseSystem system = new ExpenseSystem();

        assertNotNull(system.getExpenseList());
        assertNotNull(system.getUserList());
        assertTrue(system.getExpenseList().isEmpty());
        assertTrue(system.getUserList().isEmpty());
    }
}
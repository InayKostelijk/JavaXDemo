package nl.java.lerenjavaxexample.javaxdemo.unit;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.Expense;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.enums.ExpenseCategory;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseUnitTest {

    @Test
    public void shouldCreateExpenseCorrectly() {
        User user = new User();

        Expense expense = new Expense(
                "medicine",
                ExpenseCategory.HEALTH,
                10.0,
                user
        );

        assertEquals("medicine", expense.getItem());
        assertEquals(ExpenseCategory.HEALTH, expense.getCategory());
        assertEquals(Double.valueOf(10.0), expense.getPrice());
        assertEquals(user, expense.getUser());
    }

    @Test
    public void shouldUpdateExpenseCorrectly() {
        User user = new User();

        Expense expense = new Expense(
                "old item",
                ExpenseCategory.FOOD_DRINKS,
                5.0,
                user
        );

        expense.update(
                "new item",
                ExpenseCategory.HEALTH,
                20.0
        );

        assertEquals("new item", expense.getItem());
        assertEquals(ExpenseCategory.HEALTH, expense.getCategory());
        assertEquals(Double.valueOf(20.0), expense.getPrice());
    }

    @Test
    public void shouldKeepUserReference() {
        User user = new User();

        Expense expense = new Expense(
                "test",
                ExpenseCategory.RENT,
                100.0,
                user
        );

        assertNotNull(expense.getUser());
        assertEquals(user, expense.getUser());
    }
}
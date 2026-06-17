package nl.java.lerenjavaxexample.javaxdemo.unit;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.dto.ExpenseDto;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.mapper.ExpenseMapper;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.service.ExpenseService;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.Expense;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.enums.ExpenseCategory;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.data.ExpenseRepository;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions.BadRequestException;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions.ResourceNotFoundException;
import nl.java.lerenjavaxexample.javaxdemo.user.data.UserRepository;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
        import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceUnitTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExpenseMapper expenseMapper;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense expense;
    private ExpenseDto dto;
    private User user;

    @Before
    public void setUp() {
        user = new User();

        expense = new Expense(
                "medicine",
                ExpenseCategory.HEALTH,
                10.0,
                user
        );

        dto = new ExpenseDto(
                1L,
                "medicine",
                ExpenseCategory.HEALTH,
                10.0,
                null
        );
    }

    @Test
    public void shouldReturnAllExpenses() {
        when(expenseRepository.findAll()).thenReturn(Arrays.asList(expense));
        when(expenseMapper.toExpenseDto(expense)).thenReturn(dto);

        List<ExpenseDto> result = expenseService.getAllExpenses();

        assertEquals(1, result.size());
        verify(expenseRepository).findAll();
        verify(expenseMapper).toExpenseDto(expense);
    }

    @Test
    public void shouldReturnExpenseById() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        when(expenseMapper.toExpenseDto(expense)).thenReturn(dto);

        ExpenseDto result = expenseService.getExpenseById(1L);

        assertEquals("medicine", result.getItem());
        verify(expenseRepository).findById(1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowWhenExpenseNotFound() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        expenseService.getExpenseById(1L);
    }

    @Test
    public void shouldEditExpense() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean result = expenseService.editExpenseById(1L, dto);

        assertTrue(result);
        verify(expenseRepository).save(expense);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowWhenExpenseMissingOnEdit() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        expenseService.editExpenseById(1L, dto);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowWhenUserMissingOnEdit() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        expenseService.editExpenseById(1L, dto);
    }

    @Test
    public void shouldDeleteExpense() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        expenseService.deleteExpense(1L);

        verify(expenseRepository).delete(expense);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowWhenDeletingMissingExpense() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.empty());

        expenseService.deleteExpense(1L);
    }
}
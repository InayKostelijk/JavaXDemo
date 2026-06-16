package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.service;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.dto.ExpenseDto;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.Expense;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.ExpenseSystem;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.data.ExpenseSystemRepository;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions.ResourceNotFoundException;
import nl.java.lerenjavaxexample.javaxdemo.user.data.UserRepository;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ExpenseSystemService {

    private final ExpenseSystemRepository expenseSystemRepository;
    private final UserRepository userRepository;

    public ExpenseSystemService(
            ExpenseSystemRepository expenseSystemRepository,
            UserRepository userRepository
    ) {
        this.expenseSystemRepository = expenseSystemRepository;
        this.userRepository = userRepository;
    }

    public boolean createExpense(ExpenseDto expenseDto) {

        ExpenseSystem expenseSystem = expenseSystemRepository
                .findById("ExpenseSystem")
                .orElse(null);

        if (expenseSystem == null) {
            return false;
        }

        User user = userRepository
                .findById(expenseDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Expense expense = new Expense(
                expenseDto.getItem(),
                expenseDto.getCategory(),
                expenseDto.getPrice(),
                user
        );

        expenseSystem.createExpense(expense);

        return true;
    }
}
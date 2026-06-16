package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.service;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.dto.ExpenseDto;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.mapper.ExpenseMapper;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.data.ExpenseRepository;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.Expense;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions.BadRequestException;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation.exceptions.ResourceNotFoundException;
import nl.java.lerenjavaxexample.javaxdemo.user.data.UserRepository;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            UserRepository userRepository,
            ExpenseMapper expenseMapper
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseMapper = expenseMapper;
    }

    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseMapper::toExpenseDto)
                .collect(Collectors.toList());
    }

    public ExpenseDto getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        return expenseMapper.toExpenseDto(expense);
    }

    public boolean editExpenseById(Long expenseId, ExpenseDto expenseDto) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new BadRequestException("Expense not found"));

        User user = userRepository.findById(expenseDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        expense.update(
                expenseDto.getItem(),
                expenseDto.getCategory(),
                expenseDto.getPrice()
        );
        expenseRepository.save(expense);

        return true;
    }

    public void deleteExpense(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new BadRequestException("Expense not found"));

        expenseRepository.delete(expense);
    }
}
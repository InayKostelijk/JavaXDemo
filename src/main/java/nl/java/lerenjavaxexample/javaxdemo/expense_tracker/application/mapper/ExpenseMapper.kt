package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.mapper;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.dto.ExpenseDto;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public ExpenseDto toExpenseDto(Expense expense) {
        if (expense == null) {
            return null;
        }

        return new ExpenseDto(
                expense.getId(),
                expense.getUser() != null ? expense.getUser().getId() : null,
                expense.getItem(),
                expense.getCategory(),
                expense.getPrice(),
                expense.getCreatedAt()
        );
    }
}
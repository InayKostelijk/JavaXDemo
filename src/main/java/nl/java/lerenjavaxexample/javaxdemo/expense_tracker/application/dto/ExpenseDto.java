package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.dto;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.enums.ExpenseCategory;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ExpenseDto {

    @NotNull
    private Long userId;

    @NotNull
    private String item;

    @NotNull
    private ExpenseCategory category;

    @NotNull
    private Double price;

    private LocalDateTime createdAt;

    public ExpenseDto() {
    }

    public ExpenseDto(
                      Long userId,
                      String item,
                      ExpenseCategory category,
                      Double price,
                      LocalDateTime createdAt) {
        this.userId = userId;
        this.item = item;
        this.category = category;
        this.price = price;
        this.createdAt = createdAt;
    }


    public Long getUserId() {
        return userId;
    }

    public String getItem() {
        return item;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
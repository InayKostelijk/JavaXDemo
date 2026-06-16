package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.enums.ExpenseCategory;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private Double price;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    public Expense() {
    }

    public Expense(String item,
                   ExpenseCategory category,
                   Double price,
                   User user) {
        this.item = item;
        this.category = category;
        this.price = price;
        this.user = user;
    }

    public void update(String item,
                       ExpenseCategory category,
                       Double price) {
        this.item = item;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }
}
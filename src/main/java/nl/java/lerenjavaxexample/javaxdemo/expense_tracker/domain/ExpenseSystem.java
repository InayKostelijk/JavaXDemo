package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.enums.ExpenseCategory;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ExpenseSystem {

    @Id
    private String id = "ExpenseSystem";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "expense_system_id")
    private List<Expense> expenseList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "expense_system_id")
    private List<User> userList = new ArrayList<>();

    public ExpenseSystem() {
    }

    public Expense createExpense(Expense expense) {
        this.expenseList.add(expense);
        return expense;
    }

    public User createUser(User user) {
        this.userList.add(user);
        return user;
    }

    public String getId() {
        return id;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
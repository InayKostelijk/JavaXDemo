package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain;


import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.data.ExpenseSystemRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final ExpenseSystemRepository repo;

    public DataInitializer(ExpenseSystemRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void init() {
        if (!repo.existsById("ExpenseSystem")) {
            repo.save(new ExpenseSystem());
        }
    }
}
package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.data;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
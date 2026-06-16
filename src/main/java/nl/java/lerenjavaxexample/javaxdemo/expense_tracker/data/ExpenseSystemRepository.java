package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.data;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.ExpenseSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseSystemRepository extends JpaRepository<ExpenseSystem, String> {
}
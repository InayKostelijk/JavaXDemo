package nl.java.lerenjavaxexample.javaxdemo.expense_tracker.presentation;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.dto.ExpenseDto;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.service.ExpenseService;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.service.ExpenseSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@Validated
public class ExpenseController {

    private final ExpenseSystemService expenseSystemService;
    private final ExpenseService expenseService;

    public ExpenseController(
            ExpenseSystemService expenseSystemService,
            ExpenseService expenseService
    ) {
        this.expenseSystemService = expenseSystemService;
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {

        List<ExpenseDto> expenses = expenseService.getAllExpenses();

        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(
            @PathVariable Long id
    ) {

        ExpenseDto expense = expenseService.getExpenseById(id);

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createExpense(
            @Valid @RequestBody ExpenseDto expenseDto
    ) {

        boolean created = expenseSystemService.createExpense(expenseDto);

        if (!created) {
            return new ResponseEntity<>(
                    "Het is niet gelukt om de expense te maken",
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                "Je uitgave is toegevoegd",
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseDto expenseDto
    ) {

        boolean updated = expenseService.editExpenseById(id, expenseDto);

        if (!updated) {
            return new ResponseEntity<>(
                    "Het is niet gelukt om de expense te updaten",
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                "Het is gelukt om de expense te updaten",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id
    ) {

        expenseService.deleteExpense(id);

        return new ResponseEntity<>(
                "Je uitgave is verwijderd",
                HttpStatus.OK
        );
    }
}
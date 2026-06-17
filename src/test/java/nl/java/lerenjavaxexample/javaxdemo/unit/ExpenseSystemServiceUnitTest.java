package nl.java.lerenjavaxexample.javaxdemo.unit;

import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.dto.ExpenseDto;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.application.service.ExpenseSystemService;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.data.ExpenseSystemRepository;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.ExpenseSystem;
import nl.java.lerenjavaxexample.javaxdemo.user.data.UserRepository;
import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import nl.java.lerenjavaxexample.javaxdemo.expense_tracker.domain.enums.ExpenseCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ExpenseSystemServiceUnitTest {

    @Test
    public void createExpenseReturnsFalseWhenSystemMissing() {
        ExpenseSystemRepository systemRepo = Mockito.mock(ExpenseSystemRepository.class);
        UserRepository userRepo = Mockito.mock(UserRepository.class);
        ExpenseSystemService service = new ExpenseSystemService(systemRepo, userRepo);

        when(systemRepo.findById(any())).thenReturn(Optional.empty());

        ExpenseDto dto = new ExpenseDto(1L, "i", ExpenseCategory.FOOD_DRINKS, 1.0, null);
        boolean result = service.createExpense(dto);

        assertFalse(result);
    }

    @Test
    public void createExpenseSucceedsWhenSystemAndUserPresent() {
        ExpenseSystemRepository systemRepo = Mockito.mock(ExpenseSystemRepository.class);
        UserRepository userRepo = Mockito.mock(UserRepository.class);
        ExpenseSystem system = new ExpenseSystem();
        when(systemRepo.findById(any())).thenReturn(Optional.of(system));

        User user = new User();
        when(userRepo.findById(any())).thenReturn(Optional.of(user));

        ExpenseSystemService service = new ExpenseSystemService(systemRepo, userRepo);

        ExpenseDto dto = new ExpenseDto(1L, "i", ExpenseCategory.FOOD_DRINKS, 1.0, null);
        boolean result = service.createExpense(dto);

        assertTrue(result);
    }
}

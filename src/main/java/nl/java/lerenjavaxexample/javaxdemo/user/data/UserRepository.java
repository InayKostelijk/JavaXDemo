package nl.java.lerenjavaxexample.javaxdemo.user.data;

import nl.java.lerenjavaxexample.javaxdemo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
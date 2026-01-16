package se.alexjons987.item_registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.alexjons987.item_registry.entity.UserAccount;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);

}

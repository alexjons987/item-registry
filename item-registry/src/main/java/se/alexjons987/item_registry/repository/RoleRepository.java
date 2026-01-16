package se.alexjons987.item_registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.alexjons987.item_registry.entity.UserRole;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);

}

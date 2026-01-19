package se.alexjons987.item_registry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.alexjons987.item_registry.entity.UserAchievement;

import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<UserAchievement, Long> {

    Optional<UserAchievement> findByName(String name);

}

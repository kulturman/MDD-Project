package com.kulturman.mdd.repositories;

import com.kulturman.mdd.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO subscriptions(theme_id, user_id) VALUES (:themeId, :userId)", nativeQuery = true)
    void subscribe(long themeId, long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM subscriptions WHERE user_id = :userId AND theme_id = :themeId", nativeQuery = true)
    void unsubscribe(long themeId, long userId);

    @Query(value = "SELECT COUNT(*) FROM subscriptions WHERE user_id = :userId AND theme_id = :themeId", nativeQuery = true)
    long doesUserAlreadySubscribed(long userId, long themeId);
}

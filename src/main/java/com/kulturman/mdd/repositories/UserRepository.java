package com.kulturman.mdd.repositories;

import com.kulturman.mdd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT u from User u JOIN FETCH u.subscriptions")
    Optional<User> getUserProfile(Long id);
}

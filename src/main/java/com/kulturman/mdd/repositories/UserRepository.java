package com.kulturman.mdd.repositories;

import com.kulturman.mdd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u where u.email = ?1 OR u.username = ?1")
    Optional<User> findByEmailOrUsername(String username);
    @Query("SELECT u from User u LEFT JOIN FETCH u.subscriptions WHERE u.id = :id")
    Optional<User> getUserProfile(Long id);

    @Query("SELECT COUNT(u) = 0 from User u where (u.email = :email) and u.id != :userId")
    boolean canCurrentUserUpdateToEmail(String email, long userId);

    @Query("SELECT COUNT(u) = 0 from User u where (u.username = :username) and u.id != :userId")
    boolean canCurrentUserUpdateToUsername(String username, long userId);
}

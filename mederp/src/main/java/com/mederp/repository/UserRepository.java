
package com.mederp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.mederp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    // @Query("SELECT u FROM USER u WHERE u.username = :username OR u.email = :email")
    // Optional<User> findByUserNameOrEmail(String username, String email);

}

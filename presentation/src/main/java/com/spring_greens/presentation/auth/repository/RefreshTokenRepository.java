package com.spring_greens.presentation.auth.repository;

import com.spring_greens.presentation.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    void deleteByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO refresh_token (user_id, refresh_token, created_date, changed_date) " +
            "VALUES (:userId, :refreshToken, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) " +
            "ON DUPLICATE KEY UPDATE refresh_token = VALUES(refresh_token), changed_date = VALUES(changed_date)", nativeQuery = true)
    void insertOrUpdateRefreshToken(@Param("refreshToken") String refreshToken, @Param("userId") long userId);
}
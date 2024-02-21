package com.example.pharmdemo.repository;

import com.example.pharmdemo.models.Token;
import com.example.pharmdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository  extends JpaRepository<Token, Long> {
 Token findByUser_UsernameAndOtp(String email, String otp);
 Token findByUserId(Long id);
 Optional<Token> findByOtp(String token);

 @Query(value = "SELECT * FROM token WHERE users_id = ?1 AND (is_expired = 'false' or is_revoked = 'false')", nativeQuery = true)
 List<Token> findAllValidTokenByUser(Long id);

 //List<Token> findAllValidTokenByUser(user.getId());
 

}

package com.accumed.ae.vc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    VerificationCode findByClientID(Long clientID);
    boolean existsByClientID(Long clientID);
}

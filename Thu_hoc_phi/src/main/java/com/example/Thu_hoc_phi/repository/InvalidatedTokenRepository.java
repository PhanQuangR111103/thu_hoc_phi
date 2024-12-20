package com.example.Thu_hoc_phi.repository;

import com.example.Thu_hoc_phi.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}

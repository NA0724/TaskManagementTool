package com.tmt.TaskManagementTool.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmt.TaskManagementTool.models.VerificationToken;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String>{
    Optional<VerificationToken> findByToken(String token);
}

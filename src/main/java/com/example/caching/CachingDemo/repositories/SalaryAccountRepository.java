package com.example.caching.CachingDemo.repositories;

import com.example.caching.CachingDemo.entities.SalaryAccount;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface SalaryAccountRepository extends CrudRepository<SalaryAccount,Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SalaryAccount> findById(Long accountId);
}

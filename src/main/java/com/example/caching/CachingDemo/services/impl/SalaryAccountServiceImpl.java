package com.example.caching.CachingDemo.services.impl;

import com.example.caching.CachingDemo.entities.Employee;
import com.example.caching.CachingDemo.entities.SalaryAccount;
import com.example.caching.CachingDemo.repositories.SalaryAccountRepository;
import com.example.caching.CachingDemo.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    @Override
    public void createAccount(Employee employee) {

        //if(employee.getName().equals("Saurav")) throw new RuntimeException("Saurav is not allowed");
        SalaryAccount salaryAccount=SalaryAccount.builder()
                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();
        salaryAccountRepository.save(salaryAccount);
    }

    @Override
    @Transactional
    public SalaryAccount incrementBalance(Long accountId) {
        SalaryAccount salaryAccount=salaryAccountRepository.findById(accountId).orElseThrow(()->
                new RuntimeException("Account does not exist with id:"+accountId));
        BigDecimal prevBalance=salaryAccount.getBalance();
        BigDecimal newBalance=prevBalance.add(BigDecimal.valueOf(500));
        salaryAccount.setBalance(newBalance);
        salaryAccount=salaryAccountRepository.save(salaryAccount);
        return salaryAccount;
    }
}

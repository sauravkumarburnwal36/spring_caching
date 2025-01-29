package com.example.caching.CachingDemo.services;

import com.example.caching.CachingDemo.entities.Employee;
import com.example.caching.CachingDemo.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}

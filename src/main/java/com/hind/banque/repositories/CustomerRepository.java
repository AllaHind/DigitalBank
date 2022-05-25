package com.hind.banque.repositories;

import com.hind.banque.entities.BankAccount;
import com.hind.banque.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

package com.hind.banque;

import com.hind.banque.entities.AccountOperation;
import com.hind.banque.entities.CurrentAccount;
import com.hind.banque.entities.Customer;
import com.hind.banque.entities.SavingAccount;
import com.hind.banque.enums.AccountStatus;
import com.hind.banque.enums.OperationType;
import com.hind.banque.repositories.AccountOperationRepository;
import com.hind.banque.repositories.BankAccountRepository;
import com.hind.banque.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankApplication.class, args);
    }

  @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository)
    {
        return args -> {
            Stream.of("Hind", "Kenza", "Ibtissam").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                  customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(bankAccount -> {
                for(int i=0;i<5;i++)
                {
                    AccountOperation accountOperation=new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                    accountOperation.setBankAccount(bankAccount);
                    accountOperationRepository.save(accountOperation);

                }
            });
        };
    }

}

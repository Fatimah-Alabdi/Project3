package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;
    private final AuthRepository authRepository;



    public List<Account>getAllAccounts(){
        return accountRepository.findAll();
    }

    public List<Account> getMyAccount(Integer user_id){
        User user=authRepository.findUserById(user_id);
        return accountRepository.findAllByCustomer(user);
    }
    public  void addAccount(Integer user_id,Account account){

        User user= authRepository.findUserById(user_id);

        account.setUser(user);
        accountRepository.save(account);

    }

    public  void updateAccount(Integer account_id,Integer user_id,Account account){
        User user=authRepository.findUserById(user_id);
        Account account1=accountRepository.findAccountById(account_id);
        if (user_id!=account1.getCustomer().getId()){
            throw new ApiException("Sorry you dont have authority");

        }
        account1.setCustomer(user.getCustomer());
        account1.setAccountNumber(account.getAccountNumber());
        account1.setBalance(account.getBalance());
        accountRepository.save(account1);
    }
    public void deleteAccount(Integer account_id,Integer user_id){
        User user=authRepository.findUserById(user_id);
        Account account1=accountRepository.findAccountById(account_id);
        if (user_id!=account1.getCustomer().getId()){
            throw new ApiException("Sorry you dont have authority");

        }
        accountRepository.delete(account1);


    }


    public void activateBankAccount(Integer accountId, User user) {
       Account account=accountRepository.findAccountById(accountId);


        if (!account.getCustomer().getId().equals(user.getId())) {
            throw new ApiException("You are not authorized to activate this bank account");
        }

        if (account.getIsActive()==true) {
            throw new IllegalStateException("Bank account is already active");
        }

        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void deposit(Integer accountId, double amount, User user) {
        Account account=accountRepository.findAccountById(accountId);


        if (!account.getCustomer().getId().equals(user.getId())) {
            throw new ApiException("You are not authorized to deposit into this bank account");
        }

        if (amount <= 0) {
            throw new ApiException("Deposit amount must be greater than zero");
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }


    public void withdraw(Integer accountId, double amount, User user) {
        Account account=accountRepository.findAccountById(accountId);

        if (!account.getCustomer().getId().equals(user.getId())) {
            throw new ApiException("You are not authorized to withdraw from this bank account");
        }

        if (amount <= 0) {
            throw new ApiException("Withdrawal amount must be greater than zero");
        }

        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient funds for this withdrawal");
        }

        account.setBalance(account.getBalance() - amount);
       accountRepository.save(account);
    }
    public void transferFunds(Integer fromAccount_id, Integer toAccount_id, double amount, User user) {
        Account account=accountRepository.findAccountById(fromAccount_id);

        Account account2=accountRepository.findAccountById(toAccount_id);


        if (!account.getCustomer().getId().equals(user.getId())) {
            throw new ApiException("You are not authorized to transfer from this bank account");
        }

        if (amount <= 0) {
            throw new ApiException("Transfer amount must be greater than zero");
        }

        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient funds for this transfer");
        }


        account.setBalance(account.getBalance() - amount);


        account2.setBalance(account2.getBalance() + amount);

        accountRepository.save(account);
        accountRepository.save(account2);
    }


    public void blockAccount(Integer account_id, User user) {
        Account account=accountRepository.findAccountById(account_id);

        if (!account.getCustomer().getId().equals(user.getId())) {
            throw new ApiException("You are not authorized to block this bank account");
        }
        if (!account.getIsActive()) {
            throw new ApiException("This account is already inactive");
        }


       account.setIsActive(false);
        accountRepository.save(account);
    }
}

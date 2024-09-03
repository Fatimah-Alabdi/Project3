package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/get-all")
    public ResponseEntity getAllAccounts(@AuthenticationPrincipal User user){
return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    @GetMapping("/get-my")
    public ResponseEntity getMyAccount(@AuthenticationPrincipal User user){
       return ResponseEntity.status(200).body(accountService.getMyAccount(user.getId()));
    }
@PostMapping("/create")
public ResponseEntity createAccount(@AuthenticationPrincipal User user, @Valid @RequestBody Account account) {
    accountService.addAccount(user.getId(), account);
    return ResponseEntity.status(200).body(new ApiResponse("Account created successfully"));

}
    @PutMapping("/update/{account_id}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id, @RequestBody @Valid Account account) {
        accountService.updateAccount(account_id, user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated account  " + user.getUsername()));
    }
    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user, @PathVariable Integer account_id) {
        accountService.deleteAccount(user.getId(), account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted account " + user.getUsername()));
    }
    @PutMapping("/activate/{account_id}")
    public ResponseEntity activateBankAccount(@PathVariable Integer account_id, @AuthenticationPrincipal User user) {
        accountService.activateBankAccount(account_id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Bank account activated successfully"));
    }
    @PutMapping("/deposit/{account_id}")
    public ResponseEntity deposit(@PathVariable Integer account_id, @RequestParam double amount, @AuthenticationPrincipal User user) {
        accountService.deposit(account_id, amount, user);
        return ResponseEntity.status(200).body(new ApiResponse("Amount deposited successfully"));
    }

    @PutMapping("/withdraw/{account_id}")
    public ResponseEntity withdraw(@PathVariable Integer account_id, @RequestParam double amount, @AuthenticationPrincipal User user) {
        accountService.withdraw(account_id, amount, user);
        return ResponseEntity.status(200).body(new ApiResponse("Amount withdrawn successfully"));
    }
    @PutMapping("/transfer")
    public ResponseEntity transferFunds(@RequestParam Integer fromAccountId, @RequestParam Integer toAccountId, @RequestParam double amount, @AuthenticationPrincipal User user) {
        accountService.transferFunds(fromAccountId, toAccountId, amount, user);
        return ResponseEntity.status(200).body(new ApiResponse("Funds transferred successfully"));
    }

    @PutMapping("/block/{account_id}")
    public ResponseEntity blockAccount(@PathVariable Integer account_id, @AuthenticationPrincipal User user) {
        accountService.blockAccount(account_id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Bank account blocked successfully"));
    }
}

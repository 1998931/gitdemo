package com.ban.bankingapp.controller;

import com.ban.bankingapp.dto.AccountDto;
import com.ban.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccoutController {

    private final AccountService accountService;

    public AccoutController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
public ResponseEntity<AccountDto> getAccount(@PathVariable long id) {

        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
}

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccount() {

        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> getAccount(@PathVariable long id,@RequestBody Map<String,Double> map) {
Double amount = map.get("balance");
        return new ResponseEntity<>(accountService.deposit(id,amount), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable long id,@RequestBody AccountDto accountDto) {
        AccountDto accountDto1 = accountService.updateById(id, accountDto);
        return new ResponseEntity<>(accountDto1, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable long id) {
        String s = accountService.deleteAccount(id);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable long id,@RequestBody Map<String,Double> map) {
        Double amount = map.get("balance");
        return new ResponseEntity<>(accountService.withdraw(id,amount), HttpStatus.OK);
    }

}

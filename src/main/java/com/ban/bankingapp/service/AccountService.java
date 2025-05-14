package com.ban.bankingapp.service;

import com.ban.bankingapp.dto.AccountDto;
import com.ban.bankingapp.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(Long accountId);
    List<AccountDto> getAccounts();
AccountDto deposit(Long accountId, double amount);
AccountDto updateById(Long accountId, AccountDto accountDto);
public String deleteAccount(Long accountId);
AccountDto withdraw(Long accountId, double amount);
}

package com.ban.bankingapp.service;

import com.ban.bankingapp.dto.AccountDto;
import com.ban.bankingapp.entity.Account;
import com.ban.bankingapp.exception.AccountNotFoundException;
import com.ban.bankingapp.exception.InsufficientBalanceException;
import com.ban.bankingapp.mapper.DtoMapper;
import com.ban.bankingapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = DtoMapper.mapToAccount(accountDto);
        String random = UUID.randomUUID().toString();
        account.setAccountNumber(random);
        return DtoMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountId));

        return DtoMapper.mapToAccountDto(account);

    }

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
      List<AccountDto> accountDtoList= accounts.stream().map(DtoMapper::mapToAccountDto).toList();
      return accountDtoList;

    }

    @Override
    public AccountDto deposit(Long accountId, double amount) {
       Account account= accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountId));
       account.setBalance(account.getBalance() + amount);
        Account save = accountRepository.save(account);
        return DtoMapper.mapToAccountDto(save);

    }

    @Override
    public AccountDto updateById(Long accountId, AccountDto accountDto) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountId));
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBalance(accountDto.getBalance());
        account.setAccountHolderName(accountDto.getAccountHolderName());
        Account save = accountRepository.save(account);
        return DtoMapper.mapToAccountDto(save);


    }

    @Override
    public String deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
        return "Account deleted successfully";
    }

    @Override
    public AccountDto withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountId));
if(amount>account.getBalance()){
    throw new InsufficientBalanceException("Insufficient balance :"+account.getBalance());
}
account.setBalance(account.getBalance() - amount);
Account save = accountRepository.save(account);
return DtoMapper.mapToAccountDto(save);
    }

    }

package com.ban.bankingapp.mapper;

import com.ban.bankingapp.dto.AccountDto;
import com.ban.bankingapp.entity.Account;

public class DtoMapper {
    public static Account mapToAccount(AccountDto accountDto) {
       Account account = new Account();
       account.setId(accountDto.getId());
       account.setAccountNumber(accountDto.getAccountNumber());
       account.setBalance(accountDto.getBalance());
       account.setAccountHolderName(accountDto.getAccountHolderName());
       return account;
    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBalance(account.getBalance());
        accountDto.setAccountHolderName(account.getAccountHolderName());
        return accountDto;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akvasoft.construction.service.accountingimpl;

import com.akvasoft.construction.dto.accounting.BankDto;
import com.akvasoft.construction.entity.accounting.Bank;
import com.akvasoft.construction.repo.accounting.BankRepo;
import com.akvasoft.construction.service.accounting.BankService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chathura
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepo bankRepo;

    @Override
    public List<BankDto> loadBanks() {
        return bankRepo.findAll().stream().map(Bank::getBankDto).collect(Collectors.toList());
    }

}

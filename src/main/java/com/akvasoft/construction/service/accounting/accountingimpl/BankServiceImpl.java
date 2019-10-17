/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akvasoft.construction.service.accounting.accountingimpl;

import com.akvasoft.construction.dto.accounting.BankDetailDto;
import com.akvasoft.construction.dto.accounting.BankDto;
import com.akvasoft.construction.entity.accounting.Bank;
import com.akvasoft.construction.entity.accounting.BankDetail;
import com.akvasoft.construction.repo.accounting.BankDetailRepo;
import com.akvasoft.construction.repo.accounting.BankRepo;
import com.akvasoft.construction.service.accounting.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chathura
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private BankDetailRepo bankDetailRepo;

    @Override
    public List<BankDto> loadBanks() {
        return bankRepo.findAll().stream().map(Bank::getBankDto).collect(Collectors.toList());
    }

    @Override
    public List<BankDetailDto> loadAccountTypes(int bankId) {
        return bankDetailRepo.findByBank_BankId(bankId)
                .stream().map(bankDetail -> new BankDetail().getBankDetailDto(bankDetail)).collect(Collectors.toList());

    }


}

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
import com.akvasoft.construction.repo.ConstructionSiteRepo;
import com.akvasoft.construction.repo.accounting.BankDetailRepo;
import com.akvasoft.construction.repo.accounting.BankRepo;
import com.akvasoft.construction.service.accounting.BankService;
import com.akvasoft.construction.util.DomainConstant;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chathura
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private BankDetailRepo bankDetailRepo;

    @Autowired
    private ConstructionSiteRepo siteRepo;

    @Override
    public List<BankDto> loadBanks() {
        return bankRepo.findAll().stream().map(Bank::getBankDto).collect(Collectors.toList());
    }

    @Override
    public BankDetail saveBankDetails(BankDetailDto dto) {
        return bankDetailRepo.save(new BankDetail(dto, siteRepo.getOne(dto.getSiteId()), bankRepo.getOne(dto.getBankId()),
                DomainConstant.Status.getStatus(dto.getStatus()),
                DomainConstant.BankAccountType.getBankAccountType(dto.getType()))
        );

    }


}

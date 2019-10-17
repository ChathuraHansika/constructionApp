/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akvasoft.construction.service.accounting;

import com.akvasoft.construction.dto.accounting.BankDetailDto;
import com.akvasoft.construction.dto.accounting.BankDto;
import com.akvasoft.construction.entity.accounting.BankDetail;

import java.util.List;

/**
 * @author Chathura
 */

public interface BankService {
    List<BankDto> loadBanks();

    BankDetail saveBankDetails(BankDetailDto dto);
}

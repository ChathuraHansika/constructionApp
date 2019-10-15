/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akvasoft.construction.service.accounting;

import com.akvasoft.construction.dto.accounting.BankDto;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chathura
 */

public interface BankService {
    public List<BankDto>loadBanks();
}

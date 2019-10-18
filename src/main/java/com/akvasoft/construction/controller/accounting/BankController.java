/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akvasoft.construction.controller.accounting;

import com.akvasoft.construction.dto.accounting.BankDetailDto;
import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.service.accounting.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chathura
 */
@RestController
@RequestMapping(value = "/accounting")
public class BankController {

    @Autowired
    private BankService bankService;

    public Response saveBankDetails() {
        return null;
    }

    @GetMapping(value = "/loadBanks")
    public Response loadBanks() {
        return new Response(bankService.loadBanks(), true);
    }

    @PostMapping(value = "/saveAccount")
    public Response saveAccount(@RequestBody BankDetailDto dto) {
        return new Response(bankService.saveBankDetails(dto), true);
    }

    @GetMapping(value = "/loadAccounts")
    public Response loadAccounts() {
        return new Response(bankService.loadAccounts(), true);
    }

    @GetMapping(value = "/accountSearchByBankId")
    public Response accountSearchByBankId(@RequestParam("bankId") int bankId){
        return new Response(bankService.accountSearchByBankId(bankId),true);
    }
}

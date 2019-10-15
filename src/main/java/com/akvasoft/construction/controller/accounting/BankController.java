/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akvasoft.construction.controller.accounting;

import com.akvasoft.construction.dto.common.Response;
import com.akvasoft.construction.service.accounting.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chathura
 */
@RestController
@RequestMapping(value = "/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    public Response saveBankDetails() {
        return null;
    }

    @RequestMapping("/loadBank")
    public void loadBanks() {
        System.out.println(bankService.loadBanks());
       // return new Response(bankService.loadBanks(), true);
    }

}

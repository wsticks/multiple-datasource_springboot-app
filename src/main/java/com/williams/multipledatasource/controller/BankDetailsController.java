package com.williams.multipledatasource.controller;

import com.williams.multipledatasource.external.model.BankDetails;
import com.williams.multipledatasource.model.Tutorial;
import com.williams.multipledatasource.service.impl.BankDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class BankDetailsController {

   private final BankDetailService bankDetailService;

   @Autowired
    public BankDetailsController(BankDetailService bankDetailService) {
        this.bankDetailService = bankDetailService;
    }

    @PostMapping("/save_record")
    public Tutorial savedBankDetails(@RequestBody BankDetails bankDetails) {
        return bankDetailService.saveEmployeeBankDetails(bankDetails);
    }
}

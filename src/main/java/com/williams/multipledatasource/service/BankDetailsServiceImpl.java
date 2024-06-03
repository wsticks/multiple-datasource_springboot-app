package com.williams.multipledatasource.service;

import com.williams.multipledatasource.external.model.BankDetails;
import com.williams.multipledatasource.external.repository.BankDetailsRepository;
import com.williams.multipledatasource.model.Tutorial;
import com.williams.multipledatasource.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDetailsService {

    private final BankDetailsRepository bankDetailsRepository;
    private final TutorialRepository tutorialRepository;

    @Autowired
    public BankDetailsService(BankDetailsRepository bankDetailsRepository, TutorialRepository tutorialRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
        this.tutorialRepository = tutorialRepository;
    }
    public BankDetails saveEmployeeBankDetails(BankDetails bankDetails) {
        try{
        BankDetails savedBankDetails = new BankDetails();
        List<Tutorial> savedTutorial = tutorialRepository.findAll();
    if (savedTutorial != null) {
        savedBankDetails = bankDetailsRepository.save(bankDetails);
          }
    return savedBankDetails;
    } catch () {}
        }
}

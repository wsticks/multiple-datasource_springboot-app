package com.williams.multipledatasource.service;

import com.williams.multipledatasource.exception.CustomException;
import com.williams.multipledatasource.external.model.BankDetails;
import com.williams.multipledatasource.external.repository.BankDetailsRepository;
import com.williams.multipledatasource.model.entity.Tutorial;
import com.williams.multipledatasource.repository.TutorialRepository;
import com.williams.multipledatasource.service.impl.BankDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankDetailsServiceImpl implements BankDetailService {

    private static final Logger log = LoggerFactory.getLogger(BankDetailsServiceImpl.class);
    private final BankDetailsRepository bankDetailsRepository;
    private final TutorialRepository tutorialRepository;

    @Autowired
    public BankDetailsServiceImpl(BankDetailsRepository bankDetailsRepository, TutorialRepository tutorialRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
        this.tutorialRepository = tutorialRepository;
    }
    public Tutorial saveEmployeeBankDetails(BankDetails bankDetails) {
        try{
        Tutorial savedTutorials = new Tutorial();
        BankDetails savedBankDetails;
            savedBankDetails = bankDetailsRepository.findBankDetailsByEmployeeBankAccountNumber(
                    bankDetails.getEmployeeBankAccountNumber());
            if(savedBankDetails == null){
        throw new CustomException("Employee bank account number not found");
            }
            savedTutorials.setDescription(bankDetails.getEmployeeAccountName());
            savedTutorials.setPublished(true);
            savedTutorials.setTitle(String.valueOf(bankDetails.getEmployeeId()));
            savedTutorials = tutorialRepository.save(savedTutorials);
            log.info("Saved employee bank details {}", savedTutorials);

    return savedTutorials;
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
        }
    }

}

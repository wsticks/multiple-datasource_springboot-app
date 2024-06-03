package com.williams.multipledatasource.external.repository;

import com.williams.multipledatasource.external.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Integer> {

    public BankDetails findBankDetailsByEmployeeBankAccountNumber (String accountNumber);
}

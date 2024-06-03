package com.williams.multipledatasource.external.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "employee_bank_account_number")
    private String employeeBankAccountNumber;
    @Column(name = "employee_account_name")
    private String employeeAccountName;
    @Column(name = "emp_id")
    private String employeeId;

}

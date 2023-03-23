package org.aptech.entities;
import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "Account")
public class Account implements Serializable {

    @Id
    @Column(name = "AccountNo",columnDefinition = "varchar(20)")
    private String accountNo;


    @Column(name = "AccountName",columnDefinition = "varchar(50)")
    private String accountName;


    @Column(name = "PinCode",columnDefinition = "varchar(50)")
    private String pinCode;


    @Column(name = "Balance",columnDefinition = "decimal(15,2)")
    private double balance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "account")
    private Set<SaveTransaction> saveTransactions = new HashSet<>();
}

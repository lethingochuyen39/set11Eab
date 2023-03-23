package org.aptech.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "SaveTransaction")
public class SaveTransaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",columnDefinition = "int")
    private String id;


    @Column(name = "TranDate",columnDefinition = "date")
    private String tranDate;


    @Column(name = "Amount",columnDefinition = "decimal(15,2)")
    private double amount;


    @Column(name = "Comment",columnDefinition = "varchar(250)")
    private String comment;


    @Column(name = "AccountNo",columnDefinition = "varchar(20)")
    private String accountNo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountNo", updatable = false,insertable = false)
    private Account account;

}

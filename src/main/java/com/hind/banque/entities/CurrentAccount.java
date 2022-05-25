package com.hind.banque.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CA")
@Data
public class CurrentAccount extends BankAccount {
    private double overDraft;

}

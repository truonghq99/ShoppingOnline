package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ImportItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="bill_id",referencedColumnName = "id_bill")
    private ImportBill importBill;
    
    private int quantity=0;
    private float amount=0;
    private float discountItem=0;
    private float totalPriceItem=0;
}
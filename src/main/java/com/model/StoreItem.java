package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StoreItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="item_id",referencedColumnName = "id")
    private Item item;
    private int quantity;
    private boolean active=true;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="store_id",referencedColumnName = "id")
    private Store store;
}

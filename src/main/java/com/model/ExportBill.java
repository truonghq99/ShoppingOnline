package com.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExportBill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="bill_id", unique=true)
    private String idBill;
    
    private Date date;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="store_id",referencedColumnName = "id")
    private Store store;

    @OneToMany(mappedBy="exportBill",cascade = CascadeType.ALL)
    private List<ExportItem> listExportItem=new ArrayList<>();

    private float totalCost=0;

    
}


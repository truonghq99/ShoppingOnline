package com.controller;

import java.util.Date;
import java.sql.SQLDataException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.model.ExportBill;
import com.model.ExportItem;
import com.model.Item;
import com.model.Store;
import com.model.StoreItem;
import com.service.ExportBillService;
import com.service.ItemService;
import com.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExportController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ExportBillService exportBillService;

    // Create Bill
    @RequestMapping(value = "inventory/export-bill", method = RequestMethod.GET)
    public String showExportBill(ExportBill ExportBill, Model model) { // dung requestparam
        ArrayList<Item> items = itemService.findAll();
        ArrayList<Store> stores = storeService.findAllStore();
        for (int i = 0; i < items.size(); i++) {
            ExportItem exportItem = new ExportItem();
            exportItem.setItem(items.get(i));
            ExportBill.getListExportItem().add(exportItem);
        }
        model.addAttribute("listStore", stores);
        model.addAttribute("exportBill", ExportBill);
        return "export_bill";
    }

    @RequestMapping(value = "/reciept-export-bill", method = RequestMethod.POST)
    public String saveReciept(ExportBill reciept) {
        SimpleDateFormat sdf = new SimpleDateFormat("hhmmssddMMyyyy");
        Date date = new Date();
        String idBill = reciept.getStore().getId() + sdf.format(date);

        reciept.setIdBill(idBill);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        reciept.setDate(sqlDate);
        boolean check = false;

        ArrayList<StoreItem> items = new ArrayList<StoreItem>();

        for (int i = 0; i < reciept.getListExportItem().size(); i++) {
            if (reciept.getListExportItem().get(i).getTotalPriceItem() <= 0.0) {
                reciept.getListExportItem().remove(i);
                i--;
            } else {
                StoreItem storeItem = new StoreItem();
                storeItem.setItem(reciept.getListExportItem().get(i).getItem());
                storeItem.setQuantity(reciept.getListExportItem().get(i).getQuantity());
                storeItem.setStore(reciept.getStore());
                items.add(storeItem);

                check = itemService.updateQuantityItemExport(reciept.getListExportItem().get(i).getItem().getId(),
                        reciept.getListExportItem().get(i).getQuantity());
                reciept.getListExportItem().get(i).setExportBill(reciept);
            }
        }
        storeService.updateStoreItem(reciept.getStore().getId(), items);
        if (check == true) {
            exportBillService.createExportBill(reciept);
            return "redirect:/home";
        }
        return "redirect:/inventory/export-bill";
    }
}

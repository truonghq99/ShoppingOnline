package com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model.ImportBill;
import com.model.ImportItem;
import com.model.Item;
import com.model.Supplier;
import com.service.ImportBillService;
import com.service.ItemService;
import com.service.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImportController {

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ImportBillService importBillService;

	// Form add supplier
	@RequestMapping(value = "/home/create-supplier", method = RequestMethod.GET)
	public String showCreateSupplier(Supplier supplier, Model model) {
		model.addAttribute("supplier", supplier);
		return "create_supplier.html";
	}

	// Save supplier
	@RequestMapping(value = "/saveSupplier", method = RequestMethod.POST)
	public String saveSupplier(@ModelAttribute("supplier") Supplier supplier) {
		supplierService.createSupplier(supplier);
		return "redirect:/home/create-supplier";
	}

	// Create Bill
	@RequestMapping(value = "/import-bill", method = RequestMethod.GET)
	public String showImportBill(ImportBill importBill, Model model, HttpSession session) { // dung requestparam
		ArrayList<Supplier> listSupplier = new ArrayList<Supplier>();
		ArrayList<Item> listItem = new ArrayList<Item>();
		listSupplier = supplierService.findAll();
		listItem = itemService.findAll();
		ArrayList<ImportItem> listImportItem = new ArrayList<ImportItem>();
		for (int i = 0; i < listItem.size(); i++) {
			ImportItem ii = new ImportItem();
			ii.setItem(listItem.get(i));
			listImportItem.add(ii);
		}
		importBill.setListImportItem(listImportItem);
		model.addAttribute("listSupplier", listSupplier);
		model.addAttribute("bill", importBill);
		return "import_bill";
	}

	@RequestMapping(value = "/reciept", method = RequestMethod.POST)
	public String showreciept(@ModelAttribute("bill") ImportBill importBill, Model model) {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String id = ft.format(date);
		importBill.setIdBill(id);
		ArrayList<Item> listItem = new ArrayList<Item>();
		listItem = itemService.findAll();
		ArrayList<ImportItem> listImportItem = new ArrayList<ImportItem>();
		System.out.println(importBill.getListImportItem().size());
		for (int i = 0; i < importBill.getListImportItem().size(); i++) {
			importBill.getListImportItem().get(i).setItem(listItem.get(i));
			
			System.out.println(importBill.getListImportItem().get(i).getTotalPriceItem());
			if (importBill.getListImportItem().get(i).getTotalPriceItem() > 0) {
				importBill.getListImportItem().get(i).setImportBill(importBill);
				
			}
			else {
				importBill.getListImportItem().remove(i);
			}
		}
		// importBillService.createImportBill(importBill);
		return "reciept";
	}

}
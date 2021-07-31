package com.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import com.model.Store;
import com.model.StoreItem;
import com.service.ItemService;
import com.service.StoreItemService;
import com.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StoreController {
    
    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreItemService storeItemService;

    @GetMapping(value="/inventory/stores")
    public String showStorePage(Store store, Model model, HttpSession session) {
        ArrayList<Store> list= storeService.findAllStore();
        model.addAttribute("listStore", list);
        session.setAttribute("listStore", list);
        return "stores";
    }
    @GetMapping(value="/inventory/stores/list-store")
    public String showListStorePage(Model model, HttpSession session){
        ArrayList<Store> list= (ArrayList<Store>)session.getAttribute("listStore");
        model.addAttribute("listStore", list);
        return "list_store";
    }
    @GetMapping(value="/inventory/stores/details/{id}")
    public String showDetailsStore(@PathVariable("id")int id, Model model){
        ArrayList<StoreItem> list=storeItemService.getAllItemInStore(id);
        model.addAttribute("listItem", list);
        return "warehouse";
    }

    @GetMapping(value="/inventory/stores/create-new-store")
    public String showCreateStorePage(@RequestBody Store store, Model model){
        model.addAttribute("store", store);
        return "create_store";
    }
    @PostMapping(value="/saveStore")
    public String saveStore(Store store, Model model){
        storeService.createStore(store);
        return "redirect:/inventory/stores";
    }
}

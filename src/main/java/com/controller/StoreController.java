package com.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import com.model.Store;
import com.model.ExportItem;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String showListStorePage(Model model, Store store) {
        ArrayList<Store> list= storeService.findAllStore();
        model.addAttribute("listStore", list);
        return "list_store";
    }
    @GetMapping(value="/inventory/stores/details/{id}")
    public String showDetailsStore(@PathVariable("id")int id, Model model, Store store, HttpSession session) {
        store = storeService.findById(id);
        model.addAttribute("store", store);
        session.setAttribute("store", store);
        return "Details/details-store";
    }
    @PostMapping(value="/changeActive/true/{itemId}")
    public String activeTurnOn(@PathVariable("itemId")int id, Store store, HttpSession session) {
        store = (Store) session.getAttribute("store");
        boolean active= true;
        System.out.println(id+""+store.getId());
        storeItemService.updateStoreItem(id, store.getId(), active);
        return "redirect:/inventory/stores/details/"+store.getId();
    }
    @PostMapping(value="/changeActive/false/{itemId}")
    public String activeTurnOff(@PathVariable("itemId")int id, Store store, HttpSession session){
        store = (Store) session.getAttribute("store");
        boolean active= false;
        storeItemService.updateStoreItem(id, store.getId(), active);
        return "redirect:/inventory/stores/details/"+store.getId();
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

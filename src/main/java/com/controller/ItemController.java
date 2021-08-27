package com.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model.Book;
import com.model.Clothes;
import com.model.Electronics;
import com.model.Item;
import com.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/inventory")
    public String showInventory() {
        return "inventory";
    }

    @GetMapping("/inventory/createItem")
    public String showCreateItem(Model model, Book book, Clothes clothes, Electronics electronics) {
        book = new Book();
        electronics = new Electronics();
        clothes = new Clothes();
        model.addAttribute("book", book);
        model.addAttribute("electronics", electronics);
        model.addAttribute("clothes", clothes);
        return "createItem";
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String createBook(Book book) {
        itemService.createBook(book);
        return "home_page";
    }

    @RequestMapping(value = "/saveClothes", method = RequestMethod.POST)
    public String createClothes(Clothes clothes) {
        itemService.createClothes(clothes);
        return "home_page";
    }

    @RequestMapping(value = "/saveElectronics", method = RequestMethod.POST)
    public String createElectronics(Electronics electronics) {
        itemService.createElectronics(electronics);
        return "home_page";
    }

    @RequestMapping(value = "/inventory/list-item")
    public String showListBooks(Item item, Model model) {
        ArrayList<Item> list = itemService.findAll();
        model.addAttribute("listItem", list);
        return "List/list-item";
    }

    @RequestMapping(value = "/inventory/list-item/details/{type}/{id}", method = RequestMethod.GET)
    public String showDetailItem(@PathVariable int id, @PathVariable String type, Model model) {
        if (type.equalsIgnoreCase("book")) {
            Book book = itemService.findBookById(id);
            model.addAttribute("book", book);
            return "Details/details-book";
        } else if (type.equalsIgnoreCase("clothes")) {
            Clothes clothes = itemService.findClothesById(id);
            model.addAttribute("clothes", clothes);
            return "Details/details-clothes";
        } else if (type.equalsIgnoreCase("electronics")) {
            Electronics electronics = itemService.findElectronicsById(id);
            model.addAttribute("electronics", electronics);
            return "Details/details-electronics";
        }
        return null;
    }

    @RequestMapping(value = "/inventory/list-item/details/{type}/{id}/update", method = RequestMethod.POST)
    public String updateBook(@PathVariable int id, @PathVariable String type, Book book, Clothes clothes,
            Electronics electronics) {
        if (type.equalsIgnoreCase("book")) {
            itemService.updateBook(id, book);
        } else if (type.equalsIgnoreCase("clothes")) {
            itemService.updateClothes(id, clothes);
        } else if (type.equalsIgnoreCase("electronics")) {
            itemService.updateElectronics(id,electronics);
        }
        return "redirect:/inventory/list-item";

    }

    // Active and refresh
    @RequestMapping(value = "/active/{id}", method = { RequestMethod.GET, RequestMethod.PUT })
    public String refreshActiveItem(@PathVariable int id, Model model) {
        Item item = itemService.findById(id);
        item.setActive(true);
        itemService.saveItem(item);
        return "redirect:/inventory/list-item";
    }
}

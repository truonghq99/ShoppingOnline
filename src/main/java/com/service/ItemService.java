package com.service;

import java.util.ArrayList;

import com.model.Book;
import com.model.Clothes;
import com.model.Electronics;
import com.model.Item;

public interface ItemService {

    Item saveItem(Item item);

    Book createBook(Book book);

    Clothes createClothes(Clothes clothes);

    Electronics createElectronics(Electronics electronics);

    void updateBook(int id, Book book);

    void updateClothes(int id, Clothes clothes);

    void updateElectronics(int id, Electronics electronics);

    void deleteItemById(int id);

    ArrayList<Item> findAll();

    void updateQuantityItemImport(int id, int quantity);

    boolean updateQuantityItemExport(int id, int quantity);

    Item findById(int id);

    Book findBookById(int id);

    Clothes findClothesById(int id);

    Electronics findElectronicsById(int id);

}

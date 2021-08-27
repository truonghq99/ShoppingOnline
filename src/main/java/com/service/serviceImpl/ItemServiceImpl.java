package com.service.serviceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.model.Book;
import com.model.Clothes;
import com.model.Electronics;
import com.model.Item;
import com.repository.BookRepository;
import com.repository.ClothesRepository;
import com.repository.ElectronicsRepository;
import com.repository.ItemRepository;
import com.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ItemServiceImpl implements ItemService{
    
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private ElectronicsRepository electronicsRepository;

    @Override
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    @Override
    public Book createBook(Book book){ 
        return bookRepository.save(book);
    }
    @Override
    public Clothes createClothes(Clothes clothes){
        return clothesRepository.save(clothes);
    }
    @Override
    public Electronics createElectronics(Electronics electronics) {
        return electronicsRepository.save(electronics);
    }

    @Override
    public ArrayList<Item> findAll(){
        ArrayList<Item> list = new ArrayList<Item>();
        list=(ArrayList<Item>) itemRepository.findAll();
        return list;
    }

    @Override
    public void deleteItemById(int id){
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
        }
    }

    @Override
    public Item findById(int id) {
        if(itemRepository.existsById(id)){
            return itemRepository.getById(id);
        }
        else return null;
    }


    @Override
    public void updateQuantityItemImport(int id, int quantity) {
        if(itemRepository.existsById(id)){
            Item i = itemRepository.getById(id);
            int newQuantity= i.getQuantity()+quantity;
            i.setQuantity(newQuantity);
            itemRepository.save(i);
        }
    }

    @Override
    public boolean updateQuantityItemExport(int id, int quantity) {
        if(itemRepository.existsById(id)){
            Item i = itemRepository.getById(id);
            int newQuantity= i.getQuantity()-quantity;
            if(newQuantity>=0){
                i.setQuantity(newQuantity);
                itemRepository.save(i);
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public void updateBook(int id, Book book) {
        if(bookRepository.existsById(id)){
            bookRepository.save(book);
        }
        
    }
    @Override
    public void updateClothes(int id, Clothes clothes) {
        if(clothesRepository.existsById(id)){
            clothesRepository.save(clothes);
        }
    }
    @Override
    public void updateElectronics(int id, Electronics electronics) {
        if(electronicsRepository.existsById(id)){
            electronicsRepository.save(electronics);
        }
    }

    @Override
    public Book findBookById(int id) {
        return bookRepository.getById(id);
    }

    @Override
    public Clothes findClothesById(int id) {
        return clothesRepository.getById(id);
    }

    @Override
    public Electronics findElectronicsById(int id) {
        return electronicsRepository.getById(id);
    }

}

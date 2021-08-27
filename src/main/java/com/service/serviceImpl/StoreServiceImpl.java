package com.service.serviceImpl;

import java.util.ArrayList;

import com.model.Store;
import com.model.StoreItem;
import com.repository.StoreRepository;
import com.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public ArrayList<Store> findAllStore() {
        ArrayList<Store> list = new ArrayList<Store>();
        list = (ArrayList<Store>) storeRepository.findAll();
        return list;
    }

    @Override
    public void DeleteStore(int id) {
        storeRepository.deleteById(id);
    }

    @Override
    public void UpdateStore(Store store, int id) {
        Store t = storeRepository.findStoreById(id);
        t.setAddress(store.getAddress());
        t.setOpenDate(store.getOpenDate());
        t.setPhoneNumber(store.getPhoneNumber());
        storeRepository.save(t);
    }

    @Override
    public void updateStoreItem(int id, ArrayList<StoreItem> items) {
        Store store = storeRepository.getById(id);
        boolean check = false;
        if (!store.getStoreItem().isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                for (int j = 0; j < store.getStoreItem().size(); j++) {
                    if (store.getStoreItem().get(j).getItem().getId() == items.get(i).getItem().getId()) {
                        int quantity = store.getStoreItem().get(j).getQuantity();
                        int newQuantity = quantity + items.get(i).getQuantity();
                        store.getStoreItem().get(j).setQuantity(newQuantity);
                        check=true;
                        break;
                    }
                }
                if(check==true){
                    items.remove(i);
                    i--;
                }else if(check==false){
                    store.getStoreItem().add(items.get(i));
                }
                check=false;
            }
        }else{
            store.setStoreItem(items);
            System.out.println(store.getStoreItem().get(0).getItem().toString());
        }
    }

    @Override
    public Store findById(int id) {
        return storeRepository.getById(id);
    }

}

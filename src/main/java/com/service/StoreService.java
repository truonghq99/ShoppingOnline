package com.service;

import java.util.ArrayList;

import com.model.Store;
import com.model.StoreItem;

import org.springframework.stereotype.Service;

@Service
public interface StoreService {
    
    Store createStore(Store store);

    ArrayList<Store> findAllStore();

    void DeleteStore(int id);

    void UpdateStore(Store store, int id);

    void updateStoreItem(int id, ArrayList<StoreItem> items);

    Store findById(int id);
}
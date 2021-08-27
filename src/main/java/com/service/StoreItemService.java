package com.service;

import com.model.StoreItem;

import org.springframework.stereotype.Service;

@Service
public interface StoreItemService {
    
    StoreItem updateStoreItem(int itemId,int storeId, boolean active);
}

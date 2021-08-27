package com.service.serviceImpl;

import com.model.StoreItem;
import com.repository.StoreItemRepository;
import com.service.StoreItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreItemServiceImpl implements StoreItemService {
    @Autowired
    private StoreItemRepository repository;

    @Override
    public StoreItem updateStoreItem(int itemId, int storeId, boolean active) {
        StoreItem storeItem = repository.findByStoreIdAndItemId(storeId, itemId);
        if (storeItem != null) {
            storeItem.setActive(active);
        }
        return repository.save(storeItem);
    }

}

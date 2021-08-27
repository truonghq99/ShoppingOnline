package com.repository;

import com.model.StoreItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem, Integer>{
    
    StoreItem findByStoreIdAndItemId(int storeId, int itemId);
}

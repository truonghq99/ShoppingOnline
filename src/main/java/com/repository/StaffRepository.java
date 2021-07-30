package com.repository;



import com.model.Staff;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer>{
    
    Staff findStaffByUsername(String username);

}
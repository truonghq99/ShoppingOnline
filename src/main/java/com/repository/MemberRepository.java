package com.repository;



import com.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer>{
    Member findMemberByUsername(String username);

}
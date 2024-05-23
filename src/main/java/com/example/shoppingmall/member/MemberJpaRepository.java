package com.example.shoppingmall.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface MemberJpaRepository extends
        QueryByExampleExecutor<Member>,
        PagingAndSortingRepository<Member, Integer>,
        JpaRepository<Member, Integer> {

    Member findByUserId(String userId);

    Member save(String userId);

    Member findById(int id);
}
package com.example.shoppingmall.member;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepository {
    private final Map<String, Member> memberTable
            = new HashMap<>();

    private final DataSource dataSource;
    private final EntityManager em;

    public void makeConnection(){
        DataSourceUtils.getConnection(dataSource);
    }

    public String save(Member member) {
        // memberTable.put(member.getUserId(), member);
        em.persist(member);
//        Member savedMember = memberTable.get(member.getUserId());
        return em.find(Member.class, member.getId()).getUserId();
    }

    public Member findByUserId(String userId) {
        String jpql = "SELECT m FROM Member m WHERE m.userId = :userId";
        return em.createQuery(jpql, Member.class)
                .setParameter("userId", userId)
                .getSingleResult();
//        return em.find(Member.class, userId);
    }

    public Member findById(int id){
        return em.find(Member.class, id);
    }
}

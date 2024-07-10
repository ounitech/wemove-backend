package com.ounitech.wemove.repositories;


import com.ounitech.wemove.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}

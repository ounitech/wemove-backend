package com.ounitech.wemove.repositories;


import com.ounitech.wemove.models.Gender;
import com.ounitech.wemove.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> findByFirstname(String firstname);

    List<Member> findByActive(boolean active);

    Optional<Member> findByEmail(String email);

    long countByGender(Gender gender);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.active = true")
    long countActiveMembers();

    @Query("SELECT COUNT(m) FROM Member m WHERE m.active = false")
    long countInactiveMembers();
}

package com.ounitech.wemove.repositories;


import com.ounitech.wemove.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByFirstname(String firstname);


    //select * from member where firstname like "mou%"
    List<Member> findAllByFirstnameStartsWith(String firstname);

    List<Member> findByActive(Byte active);

    Member findByEmail(String email);


    long countByGender(String gender);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.active = 1")
    long countActiveMembers();

    @Query("SELECT COUNT(m) FROM Member m WHERE m.active = 0")
    long countInactiveMembers();


}

package com.ounitech.wemove.repositories;


import com.ounitech.wemove.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByFirstname(String firstname);


    //select * from member where firstname like "mou%"
    List<Member> findAllByFirstnameStartsWith(String firstname);

    List<Member> findByActive(Byte active);

    Member findByEmail(String email);


}

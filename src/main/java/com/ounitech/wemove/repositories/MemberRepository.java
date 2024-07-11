package com.ounitech.wemove.repositories;


import com.ounitech.wemove.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public List<Member> findByfirstname(String firstname);

    public List<Member> findByactive(Byte active);

    public Member findByemail(String email);
}

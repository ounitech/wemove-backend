package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

    List<Entry> findByMemberId(Integer memberId);
}

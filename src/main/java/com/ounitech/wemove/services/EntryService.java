package com.ounitech.wemove.services;


import com.ounitech.wemove.models.Entry;
import com.ounitech.wemove.repositories.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    public List<Entry> findEntriesByMember(Integer memberId) {
        return entryRepository.findByMemberId(memberId);
    }
}

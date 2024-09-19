package com.ounitech.wemove.services;


import com.ounitech.wemove.models.Entry;
import com.ounitech.wemove.repositories.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents a service for handling member entries related operations.
 * It includes methods to add,  retrieve member entries
 */
@Service
public class EntryService {

    private final EntryRepository entryRepository;

    /**
     * Instantiates a new Entry service.
     *
     * @param entryRepository the entry repository
     */
    EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    /**
     * Get all entries from the repository.
     *
     * @return the list of all entries
     */
    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    /**
     * Create a new entry.
     *
     * @param entry the entry object to be saved
     * @return the saved entry
     */
    public Entry createEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    /**
     * Find the member's entries by id.
     *
     * @param memberId the ID of member to find
     * @return the list of member's entries
     */
    public List<Entry> findEntriesByMember(Integer memberId) {
        return entryRepository.findByMemberId(memberId);
    }
}

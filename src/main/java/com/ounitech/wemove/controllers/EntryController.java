package com.ounitech.wemove.controllers;


import com.ounitech.wemove.models.Entry;
import com.ounitech.wemove.services.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping()
    public ResponseEntity<List<Entry>> findAllEntries() {
        List<Entry> entries = entryService.findAllEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Entry> createEntry(@RequestBody Entry input) {
        Entry savedEntry = entryService.createEntry(input);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Entry>> findEntriesByMember(@PathVariable Integer memberId) {
        List<Entry> entries = entryService.findEntriesByMember(memberId);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}

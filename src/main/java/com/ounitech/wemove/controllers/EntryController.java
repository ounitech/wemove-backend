package com.ounitech.wemove.controllers;


import com.ounitech.wemove.models.Entry;
import com.ounitech.wemove.services.EntryService;
import com.ounitech.wemove.services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntryController {

    private final EntryService entryService;
    private final MemberService memberService;

    EntryController(EntryService entryService, MemberService memberService) {
        this.entryService = entryService;
        this.memberService = memberService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Entry>> findAllEntries() {
        return new ResponseEntity<>(entryService.findAllEntries(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
        return new ResponseEntity<>(entryService.createEntry(entry), HttpStatus.CREATED);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<Entry>> findEntriesByMember(@PathVariable Integer memberId) {
        return new ResponseEntity<>(entryService.findEntriesByMember(memberId), HttpStatus.OK);
    }
}

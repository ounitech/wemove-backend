package com.ounitech.wemove.controllers;


import com.ounitech.wemove.models.StatSummary;
import com.ounitech.wemove.services.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping()
    public ResponseEntity<StatSummary> findStatSummary() {
        StatSummary statSummary = statsService.findStatSummary();
        return ResponseEntity.ok(statSummary);
    }

}

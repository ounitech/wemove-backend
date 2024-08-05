package com.ounitech.wemove.controllers;


import com.ounitech.wemove.services.StatsService;
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

    @GetMapping("/members/count")
    public long getMembersCount() {
        return statsService.getMembersCount();
    }

    @GetMapping("/active-members/count")
    public long getActiveMembersCount() {
        return statsService.getActiveMembersCount();
    }

    @GetMapping("/inactive-members/count")
    public long getInactiveMembersCount() {
        return statsService.getInactiveMembersCount();
    }

    @GetMapping("/GOLD-subscriptions/count")
    public long getGoldMembersCount() {
        return statsService.getGoldMembersCount();
    }

    @GetMapping("/SILVER-subscriptions/count")
    public long getSilverMembersCount() {
        return statsService.getSilverMembersCount();
    }

    @GetMapping("/BRONZE-subscriptions/count")
    public long getBronzeMembersCount() {
        return statsService.getBronzeMembersCount();
    }

    @GetMapping("/male-members/count")
    public long getMaleMembersCount() {
        return statsService.getMaleMembersCount();
    }

    @GetMapping("/female-members/count")
    public long getFemaleMembersCount() {
        return statsService.getFemaleMembersCount();
    }


}

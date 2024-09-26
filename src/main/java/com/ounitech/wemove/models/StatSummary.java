package com.ounitech.wemove.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StatSummary {

    private long activeMemberCount;
    private long inactiveMemberCount;
    private long totalMemberCount;
    private long goldMemberCount;
    private long silverMemberCount;
    private long bronzeMemberCount;
    private long maleMemberCount;
    private long femaleMemberCount;

    public long getActiveMemberCount() {
        return activeMemberCount;
    }

    public void setActiveMemberCount(long activeMemberCount) {
        this.activeMemberCount = activeMemberCount;
    }

    public long getInactiveMemberCount() {
        return inactiveMemberCount;
    }

    public void setInactiveMemberCount(long inactiveMemberCount) {
        this.inactiveMemberCount = inactiveMemberCount;
    }

    public long getTotalMemberCount() {
        return totalMemberCount;
    }

    public void setTotalMemberCount(long totalMemberCount) {
        this.totalMemberCount = totalMemberCount;
    }

    public long getGoldMemberCount() {
        return goldMemberCount;
    }

    public void setGoldMemberCount(long goldMemberCount) {
        this.goldMemberCount = goldMemberCount;
    }

    public long getSilverMemberCount() {
        return silverMemberCount;
    }

    public void setSilverMemberCount(long silverMemberCount) {
        this.silverMemberCount = silverMemberCount;
    }

    public long getBronzeMemberCount() {
        return bronzeMemberCount;
    }

    public void setBronzeMemberCount(long bronzeMemberCount) {
        this.bronzeMemberCount = bronzeMemberCount;
    }

    public long getMaleMemberCount() {
        return maleMemberCount;
    }

    public void setMaleMemberCount(long maleMemberCount) {
        this.maleMemberCount = maleMemberCount;
    }

    public long getFemaleMemberCount() {
        return femaleMemberCount;
    }

    public void setFemaleMemberCount(long femaleMemberCount) {
        this.femaleMemberCount = femaleMemberCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("totalMemberCount", totalMemberCount)
                .append("activeMemberCount", activeMemberCount)
                .append("inactiveMemberCount", inactiveMemberCount)
                .append("goldMemberCount", goldMemberCount)
                .append("silverMemberCount", silverMemberCount)
                .append("bronzeMemberCount", bronzeMemberCount)
                .append("maleMemberCount", maleMemberCount)
                .append("femaleMemberCount", femaleMemberCount)
                .toString();
    }
}

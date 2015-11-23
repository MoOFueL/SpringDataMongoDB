package com.moofuel.model;


/**
 * Created by MoOFueL on 23.11.2015.
 */

public class ClaimsAggregator {

    private String claimDate;
    private int count;

    public ClaimsAggregator(String claimDate, int count) {
        this.claimDate = claimDate;
        this.count = count;
    }


    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

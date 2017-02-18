package com.test_apps.slandshow.controlyourcash.model;

/**
 * Created by Admin on 18.02.2017.
 */

public class Cash {

    private int coast;
    private int income;

    public Cash(int coast, int income) {
        this.coast = coast;
        this.income = income;
    }

    public int getCoast() {
        return coast;
    }

    public int getIncome() {
        return income;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}

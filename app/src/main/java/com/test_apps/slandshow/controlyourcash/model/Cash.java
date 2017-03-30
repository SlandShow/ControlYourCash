package com.test_apps.slandshow.controlyourcash.model;

import android.widget.BaseAdapter;

/**
 * Created by Admin on 18.02.2017.
 */

public class Cash {

    private int coast;
    private int income;
    private int id;
    private String name;

    public Cash(int coast, int income, int id, String name) {
        this.coast = coast;
        this.income = income;
        this.id = id;
        this.name = name;
    }

    public int getCoast() {
        return coast;
    }

    public int getIncome() {
        return income;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}

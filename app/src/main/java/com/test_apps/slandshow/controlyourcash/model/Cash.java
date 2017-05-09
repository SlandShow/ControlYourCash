package com.test_apps.slandshow.controlyourcash.model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.BaseAdapter;

import java.util.HashMap;

/**
 * Created by Admin on 18.02.2017.
 */

public class Cash {

    private int coast;
    private int id;
    private int income;
    private String name;

    public Cash(int coast, String name, int income, int id) {
        this.coast = coast;
        this.income = income;
        this.id = id;
        this.name = name;
    }

    public Cash(String name, int income, int coast) {
        this.name = name;
        this.income = income;
        this.coast = coast;
    }

    public Cash(String name, int coast) {
        this.name = name;
        this.income = 0;
        this.coast = coast;
    }

    public Cash() {
        id = 0;
        income = 0;
        coast = 0;
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

    public void setId(int id) { this.id = id;}

    public void setName(String name) {
        this.name = name;
    }
}

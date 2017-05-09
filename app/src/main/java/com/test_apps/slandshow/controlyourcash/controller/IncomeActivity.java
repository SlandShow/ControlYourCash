package com.test_apps.slandshow.controlyourcash.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test_apps.slandshow.controlyourcash.R;
import com.test_apps.slandshow.controlyourcash.model.Cash;
import com.test_apps.slandshow.controlyourcash.model.CashListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 31.03.2017.
 */

public class IncomeActivity extends AppCompatActivity {

    private Cash cash;
    public final String[] settings = {
            "Продукты", "Кварплата", "Жильё",
            "Транспорт", "Одежда", "Развлечения"
    };
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private CashListAdapter cashListAdapter;
    private List<Cash> cashList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_settings);

    }
}

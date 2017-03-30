package com.test_apps.slandshow.controlyourcash.view;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.test_apps.slandshow.controlyourcash.R;
import com.test_apps.slandshow.controlyourcash.controller.CashAdapter;
import com.test_apps.slandshow.controlyourcash.model.Cash;
import com.test_apps.slandshow.controlyourcash.model.CashListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CashSettingsActivity extends Activity {

    private Cash cash;
    private final String[] settings = {
            "Продукты", "Кварплата", "Жильё",
            "Транспорт", "Одежда", "Развлечения"
    };
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private CashListAdapter cashListAdapter;
    private List<Cash> cashList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_settings);

        listView = (ListView) findViewById(R.id.cashListView);
        cashList = new ArrayList<>();

        // Закрепляем слушателя на listView
        for (int i = 0; i < settings.length; i++) {
            cashList.add(new Cash(0, 0, i, settings[i]));
        }

        cashListAdapter = new CashListAdapter(getApplicationContext(), cashList);
        listView.setAdapter(cashListAdapter);

        // Закрепление хэндлеров
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        "clicked " + view.getTag(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }


    public void onClickCreateCash(View view) {

    }
}

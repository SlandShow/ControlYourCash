package com.test_apps.slandshow.controlyourcash.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.test_apps.slandshow.controlyourcash.R;
import com.test_apps.slandshow.controlyourcash.controller.CashAdapter;
import com.test_apps.slandshow.controlyourcash.model.Cash;
import com.test_apps.slandshow.controlyourcash.model.CashDBHandler;
import com.test_apps.slandshow.controlyourcash.model.CashListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashSettingsActivity extends AppCompatActivity {

    private CashDBHandler dbHandler;
    private SimpleCursorAdapter simpleCursorAdapter;
    private TextView idView;
    private EditText etName;
    private EditText etCoast;
    private EditText etIncome;
    private ListView lvProducts;
    private HashMap<Long, String> map;
    private Map<Long, Integer> map_name_coas;
    private Map<Long, Integer> map_name_income;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cash_database);
        idView = (TextView) findViewById(R.id.tvID);
        etName = (EditText) findViewById(R.id.etName);
        etCoast = (EditText) findViewById(R.id.etCoast);
        etIncome = (EditText) findViewById(R.id.etIncome);
        lvProducts = (ListView) findViewById(R.id.productList);
        dbHandler = new CashDBHandler(this, null, null, 1);

        displayProductList();
        setHandler(lvProducts);
        map = dbHandler.getHashMap(CashDBHandler.COLUMN_COAST);
        String names = "";
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            names += entry.getValue() + " ";
        }
        Toast.makeText(this, names, Toast.LENGTH_LONG).show();

    }

    public void newProduct(View v) {
        try {
            Cash p = new Cash(etName.getText().toString(),
                    Integer.parseInt(etCoast.getText().toString()),
                    Integer.parseInt(etIncome.getText().toString()));
            dbHandler.addProduct(p);
            idView.setText("Record Added!");
            etName.setText("");
            etCoast.setText("");
            etIncome.setText("");
            displayProductList();
        } catch (Exception e) {
            idView.setText("Unable to add.\nTry again.");
        }
    }

    public void lookupProduct(View v) {
        Cash p = dbHandler.findProduct(etName.getText().toString());
        if (p == null) {
            idView.setText("No Match Found!");
            return;
        }
        idView.setText(String.valueOf(p.getId()));
        etCoast.setText(String.valueOf(p.getCoast()));
        etIncome.setText(String.valueOf(p.getIncome()));
    }

    public void deleteProduct(View v) {
        if (dbHandler.deleteProduct(etName.getText().toString())) {
            idView.setText("Record Deleted!");
            etName.setText("");
            etCoast.setText("");
            etIncome.setText("");
            displayProductList();
        } else {
            idView.setText("No Match \nFound!");
        }
    }

    public void updateProduct(View v) {
        try {
            Cash p = new Cash(Integer.parseInt(idView.getText().toString()),
                    etName.getText().toString(), Integer.parseInt(etCoast.getText().toString()),
                    Integer.parseInt(etIncome.getText().toString()));
            if (dbHandler.updateProduct(p)) {
                idView.setText("Product updated!");
            } else {
                idView.setText("No product \nfound!");
            }
        } catch (Exception e) {
            idView.setText("Find a product \nfirst. Check \n all fields.");
        }
        etName.setText("");
        etCoast.setText("");
        etIncome.setText("");
        displayProductList();
    }

    public void deleteAllProducts(View v) {
        dbHandler.deleteAllProducts();
        idView.setText("All products \ndeleted");
        etName.setText("");
        etCoast.setText("");
        etIncome.setText("");
        lvProducts.setAdapter(null);
    }

    private void displayProductList() {
      //  try {
            Cursor cursor = dbHandler.getAllProducts();
            if (cursor == null) {
                idView.setText("Unable to generate cursor.");
                return;
            }
            if (cursor.getCount() == 0) {
                idView.setText("No Products in the Database.");
                return;
            }
            String[] columns = new String[]{
                    CashDBHandler.COLUMN_ID,
                    CashDBHandler.COLUMN_PRODUCTNAME,
                    CashDBHandler.COLUMN_COAST,
                    CashDBHandler.COLUMN_INCOME
            };
            int[] boundTo = new int[]{
                    R.id.pId,
                    R.id.pName,
                    R.id.pCoast,
                    R.id.pIncome
            };
            simpleCursorAdapter = new SimpleCursorAdapter(this,
                    R.layout.activity_cash_settings,
                    cursor,
                    columns,
                    boundTo,
                    0);
            lvProducts.setAdapter(simpleCursorAdapter);
        //} catch (Exception ex) {
         //   idView.setText("There was an error!");
      //  }

    }

    private void setHandler(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), map.size() + " size of database", Toast.LENGTH_SHORT).show();
                displayToastInfo();
            }
        });
    }

    private void displayToastInfo() {
        String names = "";
        map = dbHandler.getHashMap(CashDBHandler.COLUMN_COAST); //
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            names += entry.getValue() + " ";
        }
        Toast.makeText(getApplicationContext(), names, Toast.LENGTH_LONG).show();
    }

}

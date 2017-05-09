package com.test_apps.slandshow.controlyourcash.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test_apps.slandshow.controlyourcash.R;

import java.util.List;

/**
 * Created by Admin on 30.03.2017.
 */

public class CashListAdapter extends BaseAdapter {

    private Context context;
    private List<Cash> cashes;
    private TextView name;
    private TextView income;
    private TextView coast;

    public CashListAdapter(Context context, List<Cash> cashes) {
        this.context = context;
        this.cashes = cashes;
    }

    @Override
    public int getCount() {
        return cashes.size();
    }

    @Override
    public Object getItem(int position) {
        return cashes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(
                context,
                R.layout.item_product_list,
                null
        );
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView income = (TextView) view.findViewById(R.id.income);
        TextView coast = (TextView) view.findViewById(R.id.coast);

        // Set views via text
        name.setText(cashes.get(position).getName());
        income.setText(cashes.get(position).getIncome() + "");
        coast.setText(cashes.get(position).getCoast() + "");

        // Save id to tag
        view.setTag(cashes.get(position).getId());

        return view;
    }

    public void update(Cash cash) {
       cash.setIncome(100);
    }

}

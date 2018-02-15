package com.example.yellowsoft.weeelco;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yellowsoft on 12/2/18.
 */

public class BookingsOrdersAdapter  extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    ArrayList<Orders> orders;

    public BookingsOrdersAdapter(Context context,ArrayList<Orders> orders){
        this.context = context;
        this.orders = orders;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View item_view = inflater.inflate(R.layout.bookings_order_list_items,null);
        TextView booking_id = (TextView) item_view.findViewById(R.id.booking_id);
        TextView date = (TextView) item_view.findViewById(R.id.date);
        TextView payment_status = (TextView) item_view.findViewById(R.id.payment_status);
        TextView current_status = (TextView) item_view.findViewById(R.id.current_status);
        TextView total_price = (TextView) item_view.findViewById(R.id.total_price);
        LinearLayout item = (LinearLayout) item_view.findViewById(R.id.item);
        booking_id.setText(orders.get(i).id);
        date.setText(orders.get(i).date);
        payment_status.setText(orders.get(i).payment_status);
        current_status.setText(orders.get(i).current_status);
        total_price.setText(orders.get(i).price);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookingsOrderDetailPage.class);
                intent.putExtra("orders",orders.get(i));
                context.startActivity(intent);
            }
        });

        return item_view;
    }
}

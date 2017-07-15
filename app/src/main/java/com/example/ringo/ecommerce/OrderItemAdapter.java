package com.example.ringo.ecommerce;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ringo.ecommerce.entity.OrderItem;

import java.util.List;

/**
 * Created by Ringo on 7/15/2017.
 */

public class OrderItemAdapter extends ArrayAdapter<OrderItem> {
    private int resourceId;
    public OrderItemAdapter(@NonNull Context context, @LayoutRes int resource,
                            @NonNull List<OrderItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItem orderItem =getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView orderItemImage = (ImageView)view.findViewById(R.id.order_item_image);
        TextView orderItemName = (TextView)view.findViewById(R.id.order_item_name);
        TextView orderItemCost = (TextView)view.findViewById(R.id.order_item_cost);
        TextView orderItemAmount = (TextView)view.findViewById(R.id.order_item_amount);
        orderItemImage.setImageResource(orderItem.getImageId());
        orderItemName.setText(orderItem.getName());
        orderItemCost.setText(orderItem.getCost());
        orderItemAmount.setText(orderItem.getAmount());
        return view;
    }
}

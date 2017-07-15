package com.example.ringo.ecommerce;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ringo.ecommerce.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class AdminFragment extends Fragment {
	private List<OrderItem> orderItemList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.admin, container, false);
//		ListView orderItemListView = (ListView) view.findViewById(R.id.orer_item_list_view);
//		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
//		OrderItem orderItem;
//		for (int i = 0; i < 2; i++) {
//			orderItem = new OrderItem(R.mipmap.ic_launcher, i + "", i, i);
//			orderItemList.add(orderItem);
//		}
//
//		OrderItemAdapter orderItemAdapter = new OrderItemAdapter(this.getActivity(),
//				R.layout.order_item, orderItemList);
//		orderItemListView.setAdapter(orderItemAdapter);
		return view;
	}

}
package com.example.ringo.ecommerce;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GoodsList extends Fragment {

    EditText searchGoods;
    ListView goodsList;
    List<Map<String,Object>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.goods_detail, container, false);
//        searchGoods = (EditText) view.findViewById(R.id.search_goods);
//        goodsList = (ListView) view.findViewById(R.id.goods_list);
//        getData();
//        final SimpleAdapter adapter = new SimpleAdapter(getActivity(),list,R.layout.goods_item
//                ,new String[]{"image","name","view","goods_name","goods_view","goods_price"}
//                ,new int[]{R.id.goods_image,R.id.name_text,R.id.view_text,R.id.goods_name_text
//                ,R.id.goods_view_text});
//        goodsList.setAdapter(adapter);
//        setListViewHeightBasedOnChildren(goodsList);
//        goodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity(),GoodsDetail.class);
//                startActivity(intent);
//
//            }
//        });
        return view;
    }

    private  void getData(){
        list= new ArrayList<Map<String,Object>>();
        for(int i = 0;i<10;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",R.mipmap.ic_launcher);
            map.put("name","商品名称");
            map.put("view","商品简介");
            map.put("goods_name","good"+i);
            map.put("goods_view","goods"+i);
            map.put("goods_price",23+i);
            list.add(map);

        }
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}

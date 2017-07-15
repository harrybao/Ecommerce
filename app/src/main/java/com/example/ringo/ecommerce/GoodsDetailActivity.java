package com.example.ringo.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsDetailActivity extends AppCompatActivity {
    LinearLayout addToCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        Intent intent = getIntent();
        addToCar = (LinearLayout) findViewById(R.id.add_to_car);
        addToCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GoodsDetailActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
            }
        });


    }
}

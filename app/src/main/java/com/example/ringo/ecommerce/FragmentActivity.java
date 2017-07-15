package com.example.ringo.ecommerce;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ImageView Choose;
    private int mark1=1;
    private int mark2=0;
    private int mark3=0;
    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    if(mark2==1){
                        animation(2,1);
                        mark2=0;
                    }
                    else if(mark3==1){
                        animation(3,1);
                        mark3=0;
                    }
                    mark1=1;
                    transaction.replace(R.id.content, new GoodsListFragment());
//                    mTextMessage.setText(R.string.title_home);
                    transaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    if(mark1==1){
                        animation(1,2);
                        mark1=0;
                    }
                    else if(mark3==1){
                        animation(3,2);
                        mark3=0;
                    }
                    mark2=1;
//                    transaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    if(mark1==1){
                        animation(1,3);
                        mark1=0;
                    }
                    else if(mark2==1){
                        animation(2,3);
                        mark2=0;
                    }
                    mark3=1;
                    transaction.replace(R.id.content, new AdminFragment());
                    transaction.commit();
                    return true;
            }

            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Choose = (ImageView) findViewById(R.id.choose_bar);
        fragmentManager = this.getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new GoodsListFragment());
        transaction.commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void animation(int i,int j){
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation translate;
        translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, (i-1), Animation.RELATIVE_TO_SELF, (j-1),
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        set.setFillAfter(true);
        set.setDuration(150);
        Choose.startAnimation(set);
    }

}

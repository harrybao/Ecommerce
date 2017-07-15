package com.example.ringo.ecommerce;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("NewApi")
public class GoodsListFragment extends Fragment implements ViewPager.OnPageChangeListener{
    EditText searchGoods;
    ListView goodsList;
    List<Map<String, Object>> list;
    private List<ImageView> vpLists;
    private LinearLayout ll_dot_group; //用来添加小圆点
    private String[] imageDescArrs;
    private TextView tv_img_desc;
    private ViewPager view_pager;

    private boolean isSwitchPager = false; //默认不切换
    private int previousPosition = 0; //默认为0

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //更新当前viewpager的 要显示的当前条目
            view_pager.setCurrentItem(view_pager.getCurrentItem() + 1);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_goods_list, container, false);
        searchGoods = (EditText) view.findViewById(R.id.search_goods);
        goodsList = (ListView) view.findViewById(R.id.goods_list);

        getData();
        final SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), list,
                R.layout.goods_item, new String[]{"image", "name", "view", "goods_name",
                "goods_view", "goods_price"}, new int[]{R.id.goods_image,
                R.id.name_text, R.id.view_text});
        goodsList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(goodsList);
        goodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),GoodsDetailActivity.class);
                startActivity(intent);

            }
        });
        initView(view);
        return view;
    }

    private void getData() {
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.mipmap.bee);
            map.put("name", "商品名称");
            map.put("view", "商品简介");
            map.put("goods_name", "good" + i);
            map.put("goods_view", "goods" + i);
            map.put("goods_price", 23 + i);
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
    private void initView(View view) {
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        ll_dot_group = (LinearLayout) view.findViewById(R.id.ll_dot_group);
        tv_img_desc = (TextView) view.findViewById(R.id.tv_img_desc);

        initViewPagerData();
        view_pager.setAdapter(new ViewpagerAdapter());

        //设置当前viewpager要显示第几个条目
        int item = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % vpLists.size());
        view_pager.setCurrentItem(item);

        //把第一个小圆点设置为白色，显示第一个textview内容
        ll_dot_group.getChildAt(previousPosition).setEnabled(true);
        tv_img_desc.setText(imageDescArrs[previousPosition]);
        //设置viewpager滑动的监听事件
        view_pager.addOnPageChangeListener(this);

        //实现自动切换的功能
        new Thread() {
            public void run() {
                while (!isSwitchPager) {
                    SystemClock.sleep(3000);
                    //拿着我们创建的handler 发消息
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();
    }

    /**
     * 初始化ViewPager的数据
     */
    private void initViewPagerData() {
        imageDescArrs = new String[]{"标题1", "标题2", "标题3", "标题4", "标题5"};
        vpLists = new ArrayList<ImageView>();
        int imgIds[] = {R.mipmap.hat, R.mipmap.shoes, R.mipmap.c, R.mipmap.d, R.mipmap.yi};
        ImageView iv;
        View dotView;

        for (int i = 0; i < imgIds.length; i++) {
            iv = new ImageView(this.getActivity());
            iv.setBackgroundResource(imgIds[i]);
            vpLists.add(iv);
            //准备小圆点的数据
            dotView = new View(getContext());
            dotView.setBackgroundResource(R.mipmap.select_pot);
            //设置小圆点的宽和高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            //设置每个小圆点之间距离
            if (i != 0) {
                params.leftMargin = 15;
            }
            dotView.setLayoutParams(params);
            //设置小圆点默认状态
            dotView.setEnabled(false);
            //把dotview加入到线性布局中
            ll_dot_group.addView(dotView);
        }
    }

    /**
     * 定义数据适配器
     */
    private class ViewpagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        //是否复用当前view对象
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        //初始化每个条目要显示的内容
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //拿着position位置 % 集合.size
            int newposition = position % vpLists.size();
            //获取到条目要显示的内容imageview
            ImageView iv = vpLists.get(newposition);
            //要把 iv加入到 container 中
            container.addView(iv);
            return iv;
        }

        //销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //移除条目
            container.removeView((View) object);
        }
    }

    //当新的页面被选中的时候调用
    @Override
    public void onPageSelected(int position) {
        //拿着position位置 % 集合.size
        int newposition = position % vpLists.size();
        //取出postion位置的小圆点 设置为true
        ll_dot_group.getChildAt(newposition).setEnabled(true);
        //把一个小圆点设置为false
        ll_dot_group.getChildAt(previousPosition).setEnabled(false);
        tv_img_desc.setText(imageDescArrs[newposition]);
        previousPosition = newposition;
    }

    @Override
    public void onDestroy() {
        //当Activity销毁的时候 把是否切换的标记置为true
        isSwitchPager = true;
        super.onDestroy();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    //当页面开始滑动
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
}

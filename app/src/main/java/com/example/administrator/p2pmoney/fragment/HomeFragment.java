package com.example.administrator.p2pmoney.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.administrator.p2pmoney.R;
import com.example.administrator.p2pmoney.bean.Image;
import com.example.administrator.p2pmoney.bean.Index;
import com.example.administrator.p2pmoney.bean.Product;
import com.example.administrator.p2pmoney.common.AppNetConfig;
import com.example.administrator.p2pmoney.util.UiUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/2/24.
 */
public class HomeFragment extends Fragment {

private AsyncHttpClient client = new AsyncHttpClient();
    @InjectView(R.id.title_left)
    ImageView titleLeft;
    @InjectView(R.id.title_tv)
    TextView titleTv;
    @InjectView(R.id.title_right)
    ImageView titleRight;
    @InjectView(R.id.vp_barner)
    ViewPager vpBarner;
    @InjectView(R.id.circle_barner)
    CirclePageIndicator circleBarner;
    @InjectView(R.id.textView1)
    TextView textView1;
    @InjectView(R.id.p_yearlv)
    TextView pYearlv;
    @InjectView(R.id.button1)
    Button button1;
private  Index index;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UiUtils.getXmlView(R.layout.fragment_home);
//        View view = View.inflate(container.getContext(), R.layout.activity_home, null);
        ButterKnife.inject(this, view);
        initTitle();
        initData();
        return view;
    }

    private void initData() {
        client.post(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(String content) {
                index = new Index();

                //建立对象
                JSONObject jsonObject = JSON.parseObject(content);
                //把json对象转换成zifuchuan
                String proInfo = jsonObject.getString("proInfo");
                Product product = JSON.parseObject(proInfo, Product.class);
                String imageArr = jsonObject.getString("imageArr");
                List<Image> imageList = JSON.parseArray(imageArr, Image.class);
                index.product=product;
                index.imageList=imageList;
                vpBarner.setAdapter(new MyAdapter(imageList));
                circleBarner.setViewPager(vpBarner);


            }

            @Override
            public void onFailure(Throwable error, String content) {
                Toast.makeText(getActivity(),"請求數百。。。。。"+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTitle() {
        
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private class MyAdapter extends PagerAdapter {
        public List<Image> imageList;

        public MyAdapter(List<Image> imageList) {
            this.imageList =imageList;
        }

        @Override
        public int getCount() {
            return imageList==null? 0:imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//不用fitXY,center_crop是按照比例
            container.addView(imageView);
            //皮擦求的用法
            Picasso.with(getActivity()).load(imageList.get(position).IMAURL).into(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            super.destroyItem(container, position, object);
        }
    }
}

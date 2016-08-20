package com.example.freshlin.xl.example.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.freshlin.R;
import com.example.freshlin.xl.example.adapter.RefreshAdapter;
import com.example.freshlin.xl.frame.ViewHolder.BaseFooterHolder;
import com.example.freshlin.xl.frame.ViewHolder.BaseHeaderHolder;
import com.example.freshlin.xl.frame.activity.BaseActivity;
import com.example.freshlin.xl.frame.decoration.DefaultItemDecoration;
import com.example.freshlin.xl.frame.listener.IAdapterListener;
import com.example.freshlin.xl.frame.listener.IFooterViewListner;
import com.example.freshlin.xl.frame.listener.IHeaderViewListner;
import com.example.freshlin.xl.frame.wigit.RfreshRecyClerView;
import com.example.freshlin.xl.frame.wigit.TestRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xl on 2016/8/15.
 */
public class TestMyRefreshViewActivity extends BaseActivity implements IAdapterListener{
    private TestRefreshRecyclerView testRefreshRecyclerView;


    private RefreshAdapter adapter;
    private List<String> datas = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.act_test_refreshview;
    }


    @Override
    public void bindView() {
        hideToolBar();
        testRefreshRecyclerView = (TestRefreshRecyclerView)findViewById(R.id.testRefreshRecyclerView);

        testRefreshRecyclerView.setRefreshListener(new RfreshRecyClerView.RefreshListener() {
            @Override
            public void pullDownRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.add(0,"0");
                        adapter.notifyDataSetChanged();
                        testRefreshRecyclerView.refreshEnd();
                    }
                }, 1000);
            }

            @Override
            public void pullUpRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.add(datas.size(), datas.size() + "");
                        adapter.notifyDataSetChanged();
                        testRefreshRecyclerView.refreshEnd();
                    }
                }, 1000);
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }
    };

    @Override
    public void bindData() {

        for(int i = 0; i < 20; ++i){
            datas.add(i + "");
        }

        try {
            adapter = new RefreshAdapter(this, datas);
            adapter.setAdapterListner(this);
            adapter.setHeaderId(R.layout.adapter_header_base);
            //adapter.setFooterId(R.layout.adapter_footer_base);

            testRefreshRecyclerView.setAdapter(adapter);
            testRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            testRefreshRecyclerView.setItemDecoration(new DefaultItemDecoration(getResources().getDrawable(R.drawable.divider_default), DefaultItemDecoration.GRID));
            adapter.notifyDataSetChanged();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }



    private IFooterViewListner footerViewListner = new IFooterViewListner() {
        @Override
        public void onBindFooterData(BaseFooterHolder holder) {

        }

        @Override
        public BaseFooterHolder onCreateFooter(View view) {
            return new FooterHolder(view);
        }

        class FooterHolder extends BaseFooterHolder{
            public FooterHolder(View itemView) {
                super(itemView);
            }
        }
    };


    private IHeaderViewListner headerViewListner = new IHeaderViewListner() {
        @Override
        public void onBindHeaderData(BaseHeaderHolder holder) {

        }

        @Override
        public BaseHeaderHolder onCreateHeader(View view) {
            return new BaseHeaderHolder(view);
        }

        class  headerHolder extends BaseHeaderHolder{
            public headerHolder(View itemView) {
                super(itemView);

            }
        }
    };

    @Override
    public void onItemClick(View view, int position, Object item) {

    }
}

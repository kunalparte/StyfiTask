package com.example.kunalparte.styfitask.DataList.views;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.kunalparte.styfitask.DataList.api.ApiResponseInterfaceImpl;
import com.example.kunalparte.styfitask.DataList.PresenterImpl.PresenterImpl;
import com.example.kunalparte.styfitask.DataList.adapters.DataItemListAdapter;
import com.example.kunalparte.styfitask.DataList.interfaces.MainPresenterInterface;
import com.example.kunalparte.styfitask.DataList.models.DataItem;
import com.example.kunalparte.styfitask.R;
import com.example.kunalparte.styfitask.Utils.BaseActivity;
import com.example.kunalparte.styfitask.Utils.Consts;
import com.example.kunalparte.styfitask.Utils.ScrollToPaginateListener;

import java.util.ArrayList;
import java.util.List;

public class DataListActivity extends BaseActivity implements MainPresenterInterface.ViewInterface{

    private RecyclerView dataRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private MainPresenterInterface.Presenter presenter;

    private DataItemListAdapter dataItemListAdapter;

    private boolean isLoading = false;

    private List<DataItem> dataItems;

    private int pageCount = 1;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onRecyclerScrolled();
    }

    public void init(){
        getSupportActionBar().setTitle("Hello, "+ getIntent().getStringExtra(Consts.NAME_KEY));
        dataRecyclerView = (RecyclerView) findViewById(R.id.dataList);
        progressBar = (ProgressBar) findViewById(R.id.footer);
        presenter = new PresenterImpl(this,new ApiResponseInterfaceImpl());
        presenter.requestDataFromServer(pageCount);
        linearLayoutManager = new LinearLayoutManager(this);
        dataRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void onRecyclerScrolled(){
        dataRecyclerView.addOnScrollListener(new ScrollToPaginateListener(linearLayoutManager) {
            @Override
            protected void loadMore() {
                isLoading = true;
                pageCount++;
                presenter.requestDataFromServer(pageCount);
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public int getTotalPageCount() {
                return pageCount;
            }
        });
    }


    @Override
    public void setDataOnRecyclerView(List<DataItem> dataItemList) {
        int prevPos =0;
        dataItems = new ArrayList<>();
        for (int i = 0; i < dataItemList.size(); i++){
            prevPos++;
            if (prevPos == 3){
                prevPos = 0;
            }else{
                dataItems.add(dataItemList.get(i));
            }
        }
        if (pageCount == 1 ) {
            dataRecyclerView.setLayoutManager(linearLayoutManager);
            dataItemListAdapter = new DataItemListAdapter(DataListActivity.this, dataItems);
            dataRecyclerView.setAdapter(dataItemListAdapter);
        }else {
         dataItemListAdapter.addAllDataItems(dataItems);
        }
        dataItemListAdapter.notifyDataSetChanged();
        isLoading = false;

    }

    @Override
    public void showFooterView(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFooterView(){
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}

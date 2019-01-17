package com.example.kunalparte.styfitask.DataList.interfaces;

import com.example.kunalparte.styfitask.DataList.models.DataItem;
import com.example.kunalparte.styfitask.DataList.models.DataModel;

import java.util.List;

public interface MainPresenterInterface {

    public interface Presenter{
        void onDestroy();

        void requestDataFromServer(int pageCount);
    }


    interface ViewInterface{
        void setDataOnRecyclerView(List<DataItem>dataItemList);
        void showFooterView();
        void hideFooterView();
    }

    public interface ApiResponseInterface{

        interface ApiCallFinished{
            void onApiCallFinished(List<DataItem> dataItemList);
            void onApiCallFailed(Throwable throwable);
        }

        public void getDataItemList(ApiCallFinished apiCallFinished, int pageNo);
    }
}

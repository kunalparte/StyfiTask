package com.example.kunalparte.styfitask.DataList.PresenterImpl;

import com.example.kunalparte.styfitask.DataList.interfaces.MainPresenterInterface;
import com.example.kunalparte.styfitask.DataList.models.DataItem;

import java.util.List;

public class PresenterImpl  implements MainPresenterInterface.Presenter, MainPresenterInterface.ApiResponseInterface.ApiCallFinished {

    private MainPresenterInterface.ApiResponseInterface apiResponseInterface;
    private MainPresenterInterface.ViewInterface viewInterface;

    public PresenterImpl(MainPresenterInterface.ViewInterface viewInterface, MainPresenterInterface.ApiResponseInterface apiResponseInterface){
        this.viewInterface = viewInterface;
        this.apiResponseInterface = apiResponseInterface;
    }

    @Override
    public void onDestroy() {
        viewInterface = null;
    }

    @Override
    public void requestDataFromServer(int pageCount) {
        viewInterface.showFooterView();
        apiResponseInterface.getDataItemList(this, pageCount);
    }

    @Override
    public void onApiCallFinished(List<DataItem> dataItemList) {
        viewInterface.setDataOnRecyclerView(dataItemList);
        viewInterface.hideFooterView();
    }

    @Override
    public void onApiCallFailed(Throwable throwable) {

    }
}

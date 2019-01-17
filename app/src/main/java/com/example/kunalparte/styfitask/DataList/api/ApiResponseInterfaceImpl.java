package com.example.kunalparte.styfitask.DataList.api;


import com.example.kunalparte.styfitask.DataList.api.ServiceGenerator;
import com.example.kunalparte.styfitask.DataList.interfaces.DataItemInterface;
import com.example.kunalparte.styfitask.DataList.interfaces.MainPresenterInterface;
import com.example.kunalparte.styfitask.DataList.models.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponseInterfaceImpl implements MainPresenterInterface.ApiResponseInterface {

    @Override
    public void getDataItemList(final ApiCallFinished apiCallFinished, int pageNo) {

        DataItemInterface dataItemInterface = ServiceGenerator.createService(DataItemInterface.class);
        Call<DataModel> dataModelCall = dataItemInterface.getDataModel(pageNo);
        dataModelCall.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                apiCallFinished.onApiCallFinished(response.body().getData());
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                apiCallFinished.onApiCallFailed(t);
            }
        });
    }
}

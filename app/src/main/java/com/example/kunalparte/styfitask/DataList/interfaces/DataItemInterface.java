package com.example.kunalparte.styfitask.DataList.interfaces;

import com.example.kunalparte.styfitask.Utils.Consts;
import com.example.kunalparte.styfitask.DataList.models.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataItemInterface {

    @GET(Consts.PATH_URL)
    Call<DataModel> getDataModel(@Query("pageNo") int pageNo);
}

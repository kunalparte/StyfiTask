package com.example.kunalparte.styfitask.DataList.models;

import java.io.Serializable;
import java.util.List;

public class DataModel implements Serializable {

    private boolean success;
    private List<DataItem> data;
    private List<ErrorModel> errors;
    private List<MetaDataModel> meta;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }

    public List<MetaDataModel> getMeta() {
        return meta;
    }

    public void setMeta(List<MetaDataModel> meta) {
        this.meta = meta;
    }
}

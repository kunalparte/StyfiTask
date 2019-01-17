package com.example.kunalparte.styfitask.DataList.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kunalparte.styfitask.DataList.models.DataItem;
import com.example.kunalparte.styfitask.Utils.GlideLoader;
import com.example.kunalparte.styfitask.R;

import java.util.List;

public class DataItemListAdapter extends RecyclerView.Adapter<DataItemListAdapter.ViewHolder> {

     private List<DataItem> dataItems;
     private Activity activity;

    public DataItemListAdapter(Activity activity, List<DataItem> dataItemList){
        this.activity = activity;
        this.dataItems = dataItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.data_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String date = dataItems.get(position).getDate();
        holder.nameTextView.setText(dataItems.get(position).getName());
        holder.dateTextView.setText(date.substring(0, date.indexOf("T")));
        holder.timeTextView.setText(date.substring(date.indexOf("T")+1));
        GlideLoader.url(activity).load(dataItems.get(position).getImage()).into(holder.dataItemImageView);
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public void addAllDataItems(List<DataItem> dataItemList){
        dataItems.addAll(dataItemList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView dateTextView;
        private TextView timeTextView;
        private ImageView dataItemImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
            dataItemImageView = (ImageView) itemView.findViewById(R.id.dataItemImageView);
        }
    }
}

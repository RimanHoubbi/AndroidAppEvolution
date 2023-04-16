package com.example.myfitnesstracker.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.model.Activity_log;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.MyViewHolder> {

    List<Activity_log> activityLogList;
    OnItemClickListener onClickListener;

    public ActivitiesAdapter(List<Activity_log> activityLogList, OnItemClickListener onClickListener){
        this.activityLogList = activityLogList;
        this.onClickListener = onClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView activityTitleTextView;
        public TextView activityDateTextView;
        public TextView activityStartTimeTextView;
        public TextView activityEndtimeTextView;
        public ImageView deleteIcon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            activityTitleTextView =(TextView) itemView.findViewById(R.id.tv_activity_name);
            activityDateTextView =(TextView) itemView.findViewById(R.id.activity_date);
            activityStartTimeTextView =(TextView) itemView.findViewById(R.id.activity_start_time);
            activityEndtimeTextView =(TextView) itemView.findViewById(R.id.activity_end_time);
            deleteIcon = (ImageView)  itemView.findViewById(R.id.deleteIcon);
        }


    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_activity,parent,false);
        return new MyViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Activity_log activityLog = activityLogList.get(position);
        holder.activityTitleTextView.setText(activityLog.getType());
        holder.activityDateTextView.setText(activityLog.getDate());
        holder.activityStartTimeTextView.setText(activityLog.getStartTime());
        holder.activityEndtimeTextView.setText(activityLog.getEndTime());
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemClick(activityLog);
            }
        });

    }



    @Override
    public int getItemCount() {
        return activityLogList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(List<Activity_log> activityLogList){
        this.activityLogList = activityLogList;
        notifyDataSetChanged();
    }


public interface OnItemClickListener{
        void onItemClick(Activity_log activityLog);
}

}

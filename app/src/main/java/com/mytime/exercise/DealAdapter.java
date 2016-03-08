package com.mytime.exercise;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.Corner;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.mytime.exercise.network.pojo.Deal;
import com.mytime.exercise.viewmodel.DealViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    private List<DealViewModel> deals;

    public DealAdapter(List<DealViewModel> deals) {
        this.deals = deals;
    }

    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_deal_list_cell, parent, false);

        return new DealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DealViewHolder holder, int position) {
        DealViewModel deal = deals.get(position);

        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(Corner.TOP_LEFT, 5)
                .oval(false)
                .build();

        Picasso.with(holder.dealerPhoto.getContext())
                .load(deal.getPhotoUrl())
                .transform(transformation)
                .into(holder.dealerPhoto);


        holder.merchantName.setText(deal.getName());
        holder.serviceName.setText(deal.getType());
        holder.distance.setText(deal.getDistance());
        holder.nextAppt.setText(deal.getNextAppt());

    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public static class DealViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.dealer_photo)
        ImageView dealerPhoto;

        @Bind(R.id.merchant_name)
        TextView merchantName;

        @Bind(R.id.service_name)
        TextView serviceName;

        @Bind(R.id.distance)
        TextView distance;

        @Bind(R.id.next_appt)
        TextView nextAppt;

        public DealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

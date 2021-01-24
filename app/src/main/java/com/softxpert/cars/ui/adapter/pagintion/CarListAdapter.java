package com.softxpert.cars.ui.adapter.pagintion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softxpert.cars.R;
import com.softxpert.cars.data.entity.CarsModel;
import com.softxpert.cars.data.entity.Datum;

import java.util.List;

/**
 * Created by falcon on 19/11/2017.
 */

public class CarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Datum> iListOfServices;

    public final int TYPE_MOVIE = 0;
    public final int TYPE_LOAD = 1;
    private Context context;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public CarListAdapter(Context applicationContext, List<Datum> modelList) {
        this.context = applicationContext;
         this.iListOfServices = modelList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_MOVIE) {
            return new MyViewHolder(inflater.inflate(R.layout.item_view, parent, false));
        } else {
            return new LoadHolder(inflater.inflate(R.layout.row_load, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (position >= getItemCount() - 1 && isMoreDataAvailable && isLoading == false && loadMoreListener != null) {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if (getItemViewType(position) == TYPE_MOVIE) {
            ((MyViewHolder) holder).bindData(iListOfServices.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!(iListOfServices.get(position).getBrand().equals("load"))) {
            return TYPE_MOVIE;
        } else {

            return TYPE_LOAD;
        }
    }

    @Override
    public int getItemCount() {
        if (iListOfServices != null) {
            return iListOfServices.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView car_name, car_year, car_used;
        ImageView car_image;

        public MyViewHolder(View view) {
            super(view);
            car_name = view.findViewById(R.id.brand);
            car_used = view.findViewById(R.id.used);
            car_year = view.findViewById(R.id.year);
            //    car_price.setPaintFlags(car_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            car_image = view.findViewById(R.id.car_image);


        }

        void bindData(final Datum carsModel) {

            car_name.setText(carsModel.getBrand() + "");
            car_year.setText(carsModel.getConstractionYear());
            car_used.setText(carsModel.getIsUsed() ? " Used " : " New ");
            Glide.with(context).load(carsModel.getImageUrl()).placeholder(R.drawable.carhold).into(car_image);


        }
    }


    public void setList(List<Datum> iListOfServices) {
        this.iListOfServices = iListOfServices;
        notifyDataSetChanged();
    }

    static class LoadHolder extends RecyclerView.ViewHolder {
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface ContactsAdapterListener {
        void onContactSelected(CarsModel contact);
    }


}
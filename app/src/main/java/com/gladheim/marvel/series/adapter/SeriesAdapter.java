package com.gladheim.marvel.series.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gladheim.marvel.BR;
import com.gladheim.marvel.R;
import com.gladheim.marvel.series.model.Serie;
import com.gladheim.marvel.series.viewmodel.SeriesItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destivar on 20/06/16.
 */
public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesHolder> {

    public static final int ITEM = 1;
    public static final int LOADING = 2;

    private List<Serie> series = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private boolean isLoadingFooterAdded;

    public void addSeries(List<Serie> data) {
        series.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Serie serie);
    }

    public Serie getItem(int position) {
        return series.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SeriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        SeriesHolder viewHolder = null;

        switch (viewType){
            case ITEM:
                viewHolder = createItemHolder(parent);
                break;
            case LOADING:
                viewHolder = createLoadingViewHolder(parent);
                break;
            default:
                break;
        }

        return viewHolder;
    }

    private SeriesHolder createItemHolder( ViewGroup parent ){

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_series, parent, false);
        SeriesHolder holder = new SeriesHolder(v, false);
        return holder;

    }

    private SeriesHolder createLoadingViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
        return new SeriesHolder(v, true);

    }

    @Override
    public void onBindViewHolder(SeriesHolder holder, int position) {

        switch (getItemViewType(position)){
            case ITEM:
                bindItemViewHolder(holder, position);
                break;
            case LOADING:
                break;
            default:
                break;
        }

    }

    private void bindItemViewHolder (SeriesHolder holder, int position){
        Serie serie = series.get( position );
        holder.getBinding().setVariable( BR.viewModel, new SeriesItemViewModel( serie ) );
        holder.getBinding().executePendingBindings();
    }



    @Override
    public int getItemCount() {
        return series.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == series.size()-1 && isLoadingFooterAdded) ? LOADING : ITEM;
    }

    public class SeriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ViewDataBinding binding;

        public SeriesHolder(View itemView, boolean loading) {
            super(itemView);
            if (loading) return;
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition(), series.get(getAdapterPosition()));
        }

    }

    public void addLoading(){
        isLoadingFooterAdded = true;
        series.add(new Serie());
        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                notifyItemInserted(series.size() - 1);
            }
        };

        handler.post(r);

    }

    public void removeLoading() {
        isLoadingFooterAdded = false;

        int position = series.size() - 1;
        Serie item = getItem(position);

        if (item != null) {
            series.remove(position);
            notifyItemRemoved(position);
        }
    }
}
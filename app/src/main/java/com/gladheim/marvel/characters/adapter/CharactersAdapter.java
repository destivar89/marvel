package com.gladheim.marvel.characters.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gladheim.marvel.BR;
import com.gladheim.marvel.R;
import com.gladheim.marvel.characters.model.Character;
import com.gladheim.marvel.characters.viewmodel.CharactersItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destivar on 12/07/16.
 */
public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersHolder>{

    public static final int ITEM = 1;
    public static final int LOADING = 2;

    private List<Character> characters = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private boolean isLoadingFooterAdded;

    public void addCharacters(List<Character> data) {
        characters.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Character character);
    }

    public Character getItem(int position) {
        return characters.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public CharactersHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CharactersHolder viewHolder = null;

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

    private CharactersHolder createItemHolder( ViewGroup parent ){

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_series, parent, false);
        CharactersHolder holder = new CharactersHolder(v, false);
        return holder;

    }

    private CharactersHolder createLoadingViewHolder(ViewGroup parent) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
        return new CharactersHolder(v, true);

    }

    @Override
    public void onBindViewHolder(CharactersHolder holder, int position) {

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

    private void bindItemViewHolder (CharactersHolder holder, int position){
        Character character = characters.get( position );
        holder.getBinding().setVariable( BR.viewModel, new CharactersItemViewModel( character ) );
        holder.getBinding().executePendingBindings();
    }



    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == characters.size()-1 && isLoadingFooterAdded) ? LOADING : ITEM;
    }

    public class CharactersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ViewDataBinding binding;

        public CharactersHolder(View itemView, boolean loading) {
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
            onItemClickListener.onItemClick(v, getAdapterPosition(), characters.get(getAdapterPosition()));
        }

    }

    public void addLoading(){
        isLoadingFooterAdded = true;
        characters.add(new Character());
        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                notifyItemInserted(characters.size() - 1);
            }
        };

        handler.post(r);

    }

    public void removeLoading() {
        isLoadingFooterAdded = false;

        int position = characters.size() - 1;
        Character item = getItem(position);

        if (item != null) {
            characters.remove(position);
            notifyItemRemoved(position);
        }
    }

}

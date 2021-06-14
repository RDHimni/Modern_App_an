package com.example.modern_app_an;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

////////////////////////////////////////////////////////////////////////////////////
/////////////******************class LoadingViewholder()***********************//////////////
////////////////////////////////////////////////////////////////////////////////////

class LoadingViewholder extends RecyclerView.ViewHolder {

    ProgressBar progressBar;

    public LoadingViewholder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
    }
}

////////////////////////////////////////////////////////////////////////////////////
/////////////******************class ItemViewholder()***********************//////////////
////////////////////////////////////////////////////////////////////////////////////

class ItemViewholder extends RecyclerView.ViewHolder {

    TextView name;

    public ItemViewholder(@NonNull View itemView, final DynamicRvAdapter.OnItemClickListener listener) {
        super(itemView);

        name = itemView.findViewById(R.id.name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position, v);
                    }
                }
            }
        });
    }
}


public class DynamicRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    LoadingMore loadingMore;
    boolean isLoading;
    Activity activity;
    private ArrayList<DynamicRvModel> model_list;
    int visibleThreshold = 5;
    int lastVisibleItem, totalItemCount;


    private OnItemClickListener mListener;


    public DynamicRvAdapter(RecyclerView recyclerView,Activity activity, ArrayList<DynamicRvModel> model_list) {
        this.model_list = model_list;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)){

                    if (loadingMore != null) loadingMore.onLoadMore();
                    isLoading = true;

                }
            }
        });

    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getItemViewType()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemViewType(int position) {
        return model_list.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity).inflate(R.layout.dynamic_rv_item,parent,false);
            return new ItemViewholder(view,mListener);
        }
        else
            if (viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(activity).inflate(R.layout.dynamic_rv_progresse_bar,parent,false);
            return new LoadingViewholder(view);
        }


        return null;
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onBindViewHolder()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewholder){
            ((ItemViewholder) holder).name.setText(model_list.get(position).getName());
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getItemCount()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return model_list.size();
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************interface OnItemClickListener()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    public interface OnItemClickListener {
        void onItemClick(int position, View itemView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************interface LoadingMore()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public  interface  LoadingMore{
        void onLoadMore();
    }

    public  void  setLoadingMore(LoadingMore loadingMore){
        this.loadingMore = loadingMore;
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************setLoaded()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void setLoaded(){
        this.isLoading = false;
    }

}


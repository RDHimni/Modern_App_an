package com.example.modern_app_an;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;


public class DashboardFragment extends Fragment {

    private RecyclerView StaticRecyclerView;
    private StaticRvAtapter staticRvAtapter;

    private RecyclerView DynamicRv;
    private DynamicRvAdapter dynamicRvAdapter;


    public DashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        ArrayList<StaticRvModel> items =  new ArrayList<>();
        items.add(new StaticRvModel(R.drawable.pizza,"Pizza"));
        items.add(new StaticRvModel(R.drawable.burger,"Burger"));
        items.add(new StaticRvModel(R.drawable.pizza,"Pizza"));
        items.add(new StaticRvModel(R.drawable.pizza,"Pizza"));
        items.add(new StaticRvModel(R.drawable.pizza,"Pizza"));
        items.add(new StaticRvModel(R.drawable.pizza,"Pizza"));
        items.add(new StaticRvModel(R.drawable.pizza,"Pizza"));
        items.add(new StaticRvModel(R.drawable.pizza,"Pizza"));

        StaticRecyclerView = root.findViewById(R.id.rv_1);
        staticRvAtapter = new StaticRvAtapter(getActivity(),items);
        StaticRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        StaticRecyclerView.setAdapter(staticRvAtapter);

        final ArrayList<DynamicRvModel> itemd = new ArrayList<>();
        itemd.add(new DynamicRvModel("A0"));
        itemd.add(new DynamicRvModel("A1"));
        itemd.add(new DynamicRvModel("A2"));
        itemd.add(new DynamicRvModel("A3"));
        itemd.add(new DynamicRvModel("A4"));
        itemd.add(new DynamicRvModel("A5"));
        itemd.add(new DynamicRvModel("A6"));
        itemd.add(new DynamicRvModel("A7"));
        itemd.add(new DynamicRvModel("A8"));
        itemd.add(new DynamicRvModel("A9"));
        itemd.add(new DynamicRvModel("A10"));
        itemd.add(new DynamicRvModel("A11"));
        itemd.add(new DynamicRvModel("A12"));
        itemd.add(new DynamicRvModel("A13"));
        itemd.add(new DynamicRvModel("A14"));
        itemd.add(new DynamicRvModel("A15"));
        itemd.add(new DynamicRvModel("A16"));
        itemd.add(new DynamicRvModel("A17"));
        itemd.add(new DynamicRvModel("A18"));
        itemd.add(new DynamicRvModel("A19"));
        itemd.add(new DynamicRvModel("A20"));
        itemd.add(new DynamicRvModel("A21"));
        itemd.add(new DynamicRvModel("A22"));
        itemd.add(new DynamicRvModel("A23"));
        itemd.add(new DynamicRvModel("A24"));
        itemd.add(new DynamicRvModel("A25"));
        itemd.add(new DynamicRvModel("A26"));
        itemd.add(new DynamicRvModel("A27"));
        itemd.add(new DynamicRvModel("A28"));
        itemd.add(new DynamicRvModel("A29"));
        itemd.add(new DynamicRvModel("A30"));
        itemd.add(new DynamicRvModel("A31"));
        itemd.add(new DynamicRvModel("A32"));
        itemd.add(new DynamicRvModel("A33"));
        itemd.add(new DynamicRvModel("A34"));
        itemd.add(new DynamicRvModel("A35"));
        itemd.add(new DynamicRvModel("A36"));
        itemd.add(new DynamicRvModel("A37"));
        itemd.add(new DynamicRvModel("A38"));
        itemd.add(new DynamicRvModel("A39"));
        itemd.add(new DynamicRvModel("A40"));


        DynamicRv = root.findViewById(R.id.rv_2);
        DynamicRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        dynamicRvAdapter = new DynamicRvAdapter(DynamicRv,getActivity(),itemd);
        DynamicRv.setAdapter(dynamicRvAdapter);


        dynamicRvAdapter.setLoadingMore(new DynamicRvAdapter.LoadingMore() {
            @Override
            public void onLoadMore() {

                if (itemd.size()<=7){
                    itemd.add(null);

                    dynamicRvAdapter.notifyItemInserted(itemd.size()-1);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            itemd.remove(itemd.size()-1);
                            dynamicRvAdapter.notifyItemRemoved(itemd.size()-1);

                            int index = itemd.size();
                            int end = index +10;

                            for (int i = index; i<end; i++){

                                String name = UUID.randomUUID().toString();
                                DynamicRvModel item = new DynamicRvModel(name);

                                itemd.add(item);
                            }

                            dynamicRvAdapter.notifyDataSetChanged();
                            dynamicRvAdapter.setLoaded();

                        }
                    },4000);
                }
                else
                    Toast.makeText(getActivity(),"Data Completed",Toast.LENGTH_SHORT).show();
            }
        });



        return root;
    }
}
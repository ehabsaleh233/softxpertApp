package com.softxpert.cars.ui.activity.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.softxpert.cars.R;
import com.softxpert.cars.data.entity.CarsModel;
import com.softxpert.cars.data.entity.Datum;
import com.softxpert.cars.data.network.InjectorUtils;
import com.softxpert.cars.databinding.ActivityMainBinding;
import com.softxpert.cars.ui.adapter.pagintion.CarListAdapter;
import com.softxpert.cars.util.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity   {
    private MainViewModel iViewModel;
    private ActivityMainBinding mainBinding;
    int index = 1;
    CarListAdapter adapter;
    List<Datum> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        obtainViewModel();
        subscribeToUI();

         mainBinding.setMainmodel(iViewModel);
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainBinding.progress.setVisibility(View.VISIBLE);

                items = new ArrayList<>();
                adapter = new CarListAdapter(getApplicationContext(),items);
                getlist(1);

                pullToRefresh.setRefreshing(false);
            }
        });
    }

    private void obtainViewModel() {
        iViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        iViewModel.setWebService(InjectorUtils.provideWinchWebService());
    }
    private void subscribeToUI() {
        mainBinding.progress.setVisibility(View.VISIBLE);


        items = new ArrayList<>();

        adapter = new CarListAdapter(getApplicationContext(),items);

        adapter.setLoadMoreListener(new CarListAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                mainBinding.rec.post(new Runnable() {
                    @Override
                    public void run() {
                        ++index;
                        getlist(index);
                    }
                });

            }
        });
        mainBinding.rec.setHasFixedSize(true);
        mainBinding.rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
         mainBinding.rec.setAdapter(adapter);
        mainBinding.rec.setItemAnimator(new DefaultItemAnimator());
        iViewModel.getmList().observe(this, new Observer<CarsModel>() {
            @Override
            public void onChanged(CarsModel listOfSubServicesResponse) {
                mainBinding.progress.setVisibility(View.GONE);
                items.addAll(listOfSubServicesResponse.getData());
                adapter.notifyDataChanged();
              }
        });
        getlist(1);
        iViewModel.getmHandleError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mainBinding.progress.setVisibility(View.GONE);

                AppUtils.getInstance().showErrorSnackBar(MainActivity.this, s, mainBinding.container, R.id.container);

            }
        });





    }
    public void getlist(int page) {
        if (AppUtils.getInstance().checkNetWork(this)) {

            iViewModel.loadCarsList(page);

        } else {
            AppUtils.getInstance().showErrorSnackBar(this, getResources().getString(R.string.no_result_found),
                    mainBinding.container, R.id.container);
        }
    }


}
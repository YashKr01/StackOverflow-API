package com.example.stackoverflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.stackoverflow.adapters.MyAdapter;
import com.example.stackoverflow.api.ApiService;
import com.example.stackoverflow.api.RetrofitInstance;
import com.example.stackoverflow.model.Items;
import com.example.stackoverflow.model.MyResponse;
import com.example.stackoverflow.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.stackoverflow.util.Constants.PAGE_SIZE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Items> ownerList;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    int page = 2;
    int pageSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);

        // initialise empty list and adapter
        setupRecyclerView();

        // setting up api call
        ApiService apiService = RetrofitInstance.getRetrofit().create(ApiService.class);

        // initial call
        loadList(apiService, page, pageSize);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    Log.d("TAG", "onScrolled: Paginate");

                }

            }
        });

    }

    // for loading list and pagination
    private void loadList(ApiService apiService, int page, int pageSize) {

        progressBar.setVisibility(View.VISIBLE);

        apiService.getResponse(page, pageSize, Constants.SITE).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ownerList.addAll(response.body().getItems());
                    adapter.submitList(ownerList);
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d("TAG", "onResponse: LIST SIZE " + ownerList.size());
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void setupRecyclerView() {
        ownerList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(Items.itemCallback, this);
        recyclerView.setAdapter(adapter);
    }

}
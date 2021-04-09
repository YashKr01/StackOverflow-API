package com.example.stackoverflow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Items> ownerList;
    private ProgressBar progressBar;
    int PAGE = 2;
    int PAGE_SIZE = 10;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);

        // initialise empty list and adapter
        setupRecyclerView();

        // setting up api call

        apiService = RetrofitInstance.getRetrofit().create(ApiService.class);

        // initial call
        loadList();

        // add scroll listener
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!recyclerView.canScrollVertically(1)) {
                    Log.d("TAG", "onScrolled: END");
                    PAGE++;

                    //load new list after scrolling to end
                    loadList();

                }

            }
        });

    }

    // for loading list and pagination
    private void loadList() {

        Log.d("TAG", "loadList: " + PAGE);
        progressBar.setVisibility(View.VISIBLE);

        apiService.getResponse(PAGE, PAGE_SIZE, Constants.SITE).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "onResponse: " + response.body()
                            .getItems().get(2).getOwner().getDisplayName());
                    int old_count = ownerList.size();
                    ownerList.addAll(response.body().getItems());
                    adapter.notifyItemRangeInserted(old_count, response.body().getItems().size());
                    Log.d("TAG", "onResponse: "+ownerList.size());
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setupRecyclerView() {
        ownerList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, ownerList);
        recyclerView.setAdapter(adapter);
    }

}
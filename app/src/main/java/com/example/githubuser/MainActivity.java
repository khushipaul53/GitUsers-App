package com.example.githubuser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.githubuser.Api.BASE_API;

public class MainActivity extends AppCompatActivity {
    private TextView tvGit;
    private RecyclerView recyclerView;
    private GitAdapter gitAdapter;
    private GitUsers gitUsers;
    private List<GitUsers> list = new ArrayList<GitUsers>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvGit = findViewById(R.id.tvGit);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<GitUsers>> call = api.getUsers();
        call.enqueue(new Callback<List<GitUsers>>() {
            @Override
            public void onResponse(Call<List<GitUsers>> call, Response<List<GitUsers>> response) {
//

                int code = response.code();
                Log.d("tag", "" + code);
                list = response.body();

                Log.d("git", "" + list.size());

//

                gitAdapter = new GitAdapter(list);

//                    newsAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                recyclerView.setAdapter(gitAdapter);
                gitAdapter.SetOnItemClickListener(new OnClickListener() {
                    @Override
                    public void OnItemClicked(GitUsers users, int j) {
                        // get current book Link.
                        String link = list.get(j).getHtmlUrl();
                        // start intent.
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(link));
                        startActivity(i);

                    }
                });


            }

            @Override
            public void onFailure(Call<List<GitUsers>> call, Throwable t) {
                Log.d("tag", "" + t.getMessage());

            }
        });


    }
}

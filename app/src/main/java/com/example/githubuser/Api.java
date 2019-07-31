package com.example.githubuser;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_API="https://api.github.com/";

    @GET("users")
    Call<List<GitUsers>>getUsers();
}

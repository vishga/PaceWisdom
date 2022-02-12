package com.pacewisdom.module2;



import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("photos")
    Call<ArrayList<PosModel>> getPosts();
}

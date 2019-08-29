package com.ly.retrofitdemo;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ly on 2019/8/29 13:37
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public interface HttpService {
    @GET("ajax.php")
    Call<Translation> getCall(@Query("a") String a, @Query("f") String f, @Query("t") String t, @Query("w") String w);
}
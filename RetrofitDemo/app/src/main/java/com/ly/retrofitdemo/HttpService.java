package com.ly.retrofitdemo;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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

    /*
    // 1.新闻类别
    // 请求头：Content-Type:application/x-www-form-urlencoded
    String newsCategories = "http://api.shujuzhihui.cn/api/news/categories?appKey=be3ac46843914f5cbe875defccd8f392";

    -----------------------------------------------------------------------------------------------------------------

    // 2.新闻列表
    // 请求头：Content-Type:application/json
    // 请求体：
    {
        "category": "要闻",
        "page": "1"
    }
    String newsList = "http://api.shujuzhihui.cn/api/news/list?appKey=be3ac46843914f5cbe875defccd8f392";

    -----------------------------------------------------------------------------------------------------------------

    // 3.新闻详情
    // 请求头（header）：Content-Type:application/x-www-form-urlencoded
    // 请求内容（body）：newsId:82264991a5932770734bbd386aebedf6
    {
       "appKey": "be3ac46843914f5cbe875defccd8f392",
       "newsId": "82264991a5932770734bbd386aebedf6"
    }

    String newsDetail = "http://api.shujuzhihui.cn/api/news/detail?appKey=be3ac46843914f5cbe875defccd8f392";
    */
    // 新闻详情
    @POST("detail")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<ResponseBody> getDetail(@Field("appKey") String appKey, @Field("newsId") String newsId);

    // 新闻列表
    @POST("list?appKey=be3ac46843914f5cbe875defccd8f392")
    @Headers("Content-Type:application/json")
    Call<ResponseBody> getList(@Body RequestBody requestBody);
}
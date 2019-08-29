package com.ly.retrofitdemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //  http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=helloworld
    String BASE_URL_GET = "http://fy.iciba.com/";

    String BASE_URL_POST = "http://api.shujuzhihui.cn/api/news/";
    Button get;
    Button post;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get = findViewById(R.id.get);
        post = findViewById(R.id.post);
        text = findViewById(R.id.text);

        bindEvent();
    }

    private void bindEvent() {
       get.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl(BASE_URL_GET)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();
               HttpService service =  retrofit.create(HttpService.class);

               Call<Translation> call  =  service.getCall("fy","auto","auto","helloworld");
               call.enqueue(new Callback<Translation>() {
                   @Override
                   public void onResponse(Call<Translation> call, final Response<Translation> response) {
                       Log.d("lylog","get response =  "+response.body().getContent().getOut());
                       text.post(new Runnable() {
                           @Override
                           public void run() {
                               text.setText(response.body().getContent().getOut());
                           }
                       });
                   }

                   @Override
                   public void onFailure(Call<Translation> call, Throwable t) {
                       Log.d("lylog","get onFailure t ="+t.toString());
                   }
               });
           }
       });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL_POST)
                        .build();
                HttpService service =  retrofit.create(HttpService.class);

                MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");

                // 获取到请求内容
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("category", "要闻");
                    jsonObject.put("page", "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String str = jsonObject.toString();

                // 获取到请求体（RequestBody）
                final RequestBody requestBody = RequestBody.create(mediaType, str);

                // 3.获取call对象
                final Call<ResponseBody> call = service.getList(requestBody);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                        Log.d("lylog","post response =  "+response);
                        text.post(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(response.toString());
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("lylog","post onFailure t= "+t.toString());
                    }
                });


//                final Call<ResponseBody> call = service.getDetail("be3ac46843914f5cbe875defccd8f392", "82264991a5932770734bbd386aebedf6");
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                        Log.d("lylog","post response =  "+response);
//                        text.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                text.setText(response.toString());
//                            }
//                        });
//                    }
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.d("lylog","post onFailure t= "+t.toString());
//                    }
//                });
            }
        });


    }

}

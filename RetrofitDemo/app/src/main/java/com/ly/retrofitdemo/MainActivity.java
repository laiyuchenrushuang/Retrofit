package com.ly.retrofitdemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //  http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=helloworld
    String BASE_URL = "http://fy.iciba.com/";
    Button get;
    Button post;
    Button file;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get = findViewById(R.id.get);
        post = findViewById(R.id.post);
        file = findViewById(R.id.file);
        text = findViewById(R.id.text);

        bindEvent();
    }

    private void bindEvent() {
       get.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();
               HttpService service =  retrofit.create(HttpService.class);

               Call<Translation> call  =  service.getCall("fy","auto","auto","helloworld");
               call.enqueue(new Callback<Translation>() {
                   @Override
                   public void onResponse(Call<Translation> call, final Response<Translation> response) {
                       Log.d("lylog","response =  "+response.body().getContent().getOut());
                       text.post(new Runnable() {
                           @Override
                           public void run() {
                               text.setText(response.body().getContent().getOut());
                           }
                       });
                   }

                   @Override
                   public void onFailure(Call<Translation> call, Throwable t) {
                       Log.d("lylog","onFailure ");
                   }
               });
           }
       });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}

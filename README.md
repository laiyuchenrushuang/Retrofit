# Retrofit
### 定义

一套基于Okhttp网络请求的框架

### 使用方法

##### 1.环境
    依赖：
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.0.2'
    权限：
    <uses-permission android:name="android.permission.INTERNET"/>
    
##### 2.使用步骤
     Get请求 ： http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=helloworld

    （一） 构建retrofit对象
    Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();
    （二）构建访问http的接口
    
    public interface HttpService {
    @GET("ajax.php")
    Call<Translation> getCall(@Query("a") String a, @Query("f") String f, @Query("t") String t, @Query("w") String w);
}

    （三）创建接口对象，并传入参数
    
    HttpService service =  retrofit.create(HttpService.class);
    Call<Translation> call  =  service.getCall("fy","auto","auto","helloworld");
    
    （四）异步请求服务器响应
     call.enqueue(new Callback<Translation>(){
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
       
### 如何构建接口

      BaseURL = http://102.10.10.132/api/
      
    （一）接口方式    
    备注：Get请求ajax.php后面不用补“/” “？”之类的，因为会自动填充。  
    1.@GET请求之后 可以直接填写全的url地址
      http://102.10.10.132/api/News
      @GET("News")
      Call<NewsBean> getItem();
    2.如果Post请求，@POST
      （二里面有）
    3.@DELETE
          @DELETE("Comments/{commentId}")
          Call<ResponseBody> deleteNewsCommentFromAccount(@Path("commentId") String commentId);
    4.@PUT
          @PUT("Accounts/{accountId}")
          Call<ExtrasBean> updateExtras( @Path("accountId") String accountId,@Query("access_token") String access_token,@Body ExtrasBean bean);
    （二）注解的样式：
    1.@Query 传参数a = b 型
          http://102.10.10.132/api/News?newsId=1
          @GET("News")
          Call<NewsBean> getItem(@Query("newsId") String newsId);
    2.@Path  加载路径上，这个可以在BaseUrl的基础之上再定义接口url补充    
          http://102.10.10.132/api/News/1   
          @GET("News/{newsId}")
          Call<NewsBean> getItem(@Path("newsId") String newsId);
    3.@Field  需要补全URL，问号后加入access_token，post的数据只有一条reason
          http://102.10.10.132/api/Comments/1
          @POST("Comments/{newsId}")
          Call<Comment> reportComment(@Path("newsId") String commentId,@Field("reason") String reason);
    4.@Body   需要补全URL，问号后加入access_token，post一个body（对象）
          http://102.10.10.132/api/Comments/1?access_token=1234123
          @POST("Comments/{newsId}")
          Call<Comment> reportComment(@Path("newsId") String commentId,@Query("access_token") String access_token,@Body CommentBean bean);
    5.@QueryMap  多个参数在URL问号之后，且个数不确定
          http://102.10.10.132/api/News?newsId=1&type=类型1...
          @GET("News")
          Call<NewsBean> getItem(@QueryMap Map<String, String> map);
          
     @Path：所有在网址中的参数（URL的问号前面），如：http://102.10.10.132/api/Accounts/{accountId}
     @Query：URL问号后面的参数，如：http://102.10.10.132/api/Comments?access_token={access_token}
     @QueryMap：相当于多个@Query
     @Field：用于POST请求，提交单个数据
     @Body：相当于多个@Field，以对象的形式提交


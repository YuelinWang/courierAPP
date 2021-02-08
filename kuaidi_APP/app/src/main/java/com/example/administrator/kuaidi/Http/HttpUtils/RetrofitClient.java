package com.example.administrator.kuaidi.Http.HttpUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.kuaidi.Http.HttpApi.ApiService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final int DEFAULT_TIMEOUT = 5;
    private ApiService apiService;
    private OkHttpClient okHttpClient;
    public static String baseUrl = ApiService.Base_Url;
    private static Context mContext;
    private static RetrofitClient sNewInstance;

    private final Charset UTF8 = Charset.forName("UTF-8");


    private static class SingleonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(mContext);
    }

    public static void SetUrl(String url){

        baseUrl = url + ApiService.Urlmiddle;;//"/shijian-admin/app/";
        Log.e("++", "SetUrl: "  + url + " baseUrl:" +  baseUrl);
    }

    public static RetrofitClient getInstance(Context context){
        if(context != null){
            Log.d("RetrofitClient", "getInstance: context " );
            mContext = context;
        }
        return SingleonHolder.INSTANCE;
    }

    private static RetrofitClient getInstance(Context context, String url){
        if(context != null){
            mContext = context;
        }
        sNewInstance = new RetrofitClient(context, url);
        return sNewInstance;
    }

    private RetrofitClient(Context context){
        this(context, null);
    }

    private RetrofitClient(Context context, String url){
        if(TextUtils.isEmpty(url)){
            url = baseUrl;
        }

        //声明日志类
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设定日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                //.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel)
                //.cookieJar(new NovateCookeManager(context))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        RequestBody requestBody = request.body();
                        String body = null;
                        if(requestBody != null){
                            Buffer buffer = new Buffer();
                            requestBody.writeTo(buffer);

                            Charset charset = UTF8;
                            MediaType contentType = requestBody.contentType();
                            if(contentType != null){
                                charset = contentType.charset(UTF8);
                            }
                            body = buffer.readString(charset);
                        }
                        Log.e("++", "intercept: " + request.body() + "  " + request.url() + " " + body);

                        long startNs = System.nanoTime();
                        Response response = chain.proceed(request);
                        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

                        ResponseBody responseBody = response.body();
                        String rBody = null;

                        //if(HttpE.hasBody(response))
                        {
                            BufferedSource source = responseBody.source();
                            source.request(Long.MAX_VALUE); // Buffer the entire body.
                            Buffer buffer = source.buffer();

                            Charset charset = UTF8;
                            MediaType contentType = responseBody.contentType();
                            if (contentType != null) {
                                try {
                                    charset = contentType.charset(UTF8);
                                } catch (UnsupportedCharsetException e) {
                                    e.printStackTrace();
                                }
                            }
                            rBody = buffer.clone().readString(charset);
                            Log.e("++", "intercept: " + body + "  " + request.url() + " " + rBody);

                        }
                        return response;
                    }
                })
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        Log.e("++", "RetrofitClient: " + url +"-----" );
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService CreateBaseApi(){
        return apiService;
    }
}

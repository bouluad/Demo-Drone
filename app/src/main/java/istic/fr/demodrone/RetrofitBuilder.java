package istic.fr.demodrone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bouluad on 15/02/17.
 */
public class RetrofitBuilder {

    public static final String BASE_URL = "http://148.60.11.238:4000";

    public static WebServerIntf getSimpleClient() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                //you need to add your root url
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        WebServerIntf webServer = retrofit.create(WebServerIntf.class);
        return webServer;
    }
}
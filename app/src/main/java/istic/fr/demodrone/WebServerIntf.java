package istic.fr.demodrone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bouluad on 15/02/17.
 */
public interface WebServerIntf {

    @GET("/position")
    Call<PositionDrone> getLastPosition();

    @GET("position/{id}")
    Call<PositionDrone> getPositionById(@Path("id") int id);

    @GET("position")
    Call<List<PositionDrone>> getPositionList();
}
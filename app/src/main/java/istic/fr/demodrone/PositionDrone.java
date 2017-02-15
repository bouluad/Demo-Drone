package istic.fr.demodrone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bouluad on 15/02/17.
 */
public class PositionDrone {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("dated")
    @Expose
    private String dated;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("position")
    @Expose
    private List<Double> position = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<Double> getPosition() {
        return position;
    }

    public void setPosition(List<Double> position) {
        this.position = position;
    }

}
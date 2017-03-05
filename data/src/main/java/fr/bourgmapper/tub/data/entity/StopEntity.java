package fr.bourgmapper.tub.data.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
/**
 * Stop Entity used in the data layer.
 */
@Entity
public class StopEntity {
    public static final String TABLE_NAME = "Stops";

    @SerializedName("id")
    @Id
    public long stopId;

    @SerializedName("label")
    @NotNull
    public String label;

    @SerializedName("latitude")
    @NotNull
    public String latitude;

    @SerializedName("longitude")
    @NotNull
    public String longitude;

    @Generated(hash = 3792568)
    public StopEntity(long stopId, @NotNull String label, @NotNull String latitude,
            @NotNull String longitude) {
        this.stopId = stopId;
        this.label = label;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Generated(hash = 779355278)
    public StopEntity() {
    }

    public long getStopId() {
        return this.stopId;
    }

    public void setStopId(long stopId) {
        this.stopId = stopId;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

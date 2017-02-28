package fr.bourgmapper.tub.data.entity;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Stop Entity used in the data layer.
 */
@Table(name = StopEntity.TABLE_NAME, database = CacheDatabase.class)
public class StopEntity extends BaseModel {
    public static final String TABLE_NAME = "Stops";

    @SerializedName("id")
    @Column
    @PrimaryKey
    public String stopId;

    @SerializedName("label")
    @Column
    public String label;

    @SerializedName("latitude")
    @Column
    public String latitude;

    @SerializedName("longitude")
    @Column
    public String longitude;
}

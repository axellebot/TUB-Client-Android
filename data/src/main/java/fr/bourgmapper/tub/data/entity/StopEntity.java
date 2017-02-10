package fr.bourgmapper.tub.data.entity;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class)
public class StopEntity extends BaseModel{
    @SerializedName("id")
    @Column
    @PrimaryKey
    public String id;

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

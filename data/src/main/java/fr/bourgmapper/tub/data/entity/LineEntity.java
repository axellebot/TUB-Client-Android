package fr.bourgmapper.tub.data.entity;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class)
public class LineEntity extends BaseModel {
    @SerializedName("id")
    @Column
    @PrimaryKey
    public String id;

    @SerializedName("number")
    @Column
    public String number;

    @SerializedName("label")
    @Column
    public String label;

    @SerializedName("color")
    @Column
    public String color;

    @SerializedName("order")
    @Column
    public String order;

    @SerializedName("kml_path")
    @Column
    public String kmlPath;
}

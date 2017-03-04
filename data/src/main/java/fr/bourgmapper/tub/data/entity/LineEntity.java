package fr.bourgmapper.tub.data.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Line Entity used in the data layer.
 */
@Entity
public class LineEntity {
    public static final String TABLE_NAME = "Lines";

    @SerializedName("id")
    @Id
    public long lineId;

    @SerializedName("number")
    @NotNull
    public String number;

    @SerializedName("label")
    @NotNull
    public String label;

    @SerializedName("color")
    @NotNull
    public String color;

    @SerializedName("order")
    @NotNull
    public String order;

    @SerializedName("kml_path")
    @NotNull
    public String kmlPath;

    @Generated(hash = 1886063558)
    public LineEntity(int lineId, @NotNull String number, @NotNull String label,
                      @NotNull String color, @NotNull String order, @NotNull String kmlPath) {
        this.lineId = lineId;
        this.number = number;
        this.label = label;
        this.color = color;
        this.order = order;
        this.kmlPath = kmlPath;
    }

    @Generated(hash = 1586593410)
    public LineEntity() {
    }

    public long getLineId() {
        return this.lineId;
    }

    public void setLineId(long lineId) {
        this.lineId = lineId;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getKmlPath() {
        return this.kmlPath;
    }

    public void setKmlPath(String kmlPath) {
        this.kmlPath = kmlPath;
    }
}

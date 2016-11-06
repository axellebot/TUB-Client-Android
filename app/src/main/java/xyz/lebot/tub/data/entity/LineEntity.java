package xyz.lebot.tub.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by axell on 04/11/2016.
 */

public class LineEntity {
    @SerializedName("id")
    private String id;
    @SerializedName("number")
    private String number;
    @SerializedName("label")
    private String label;
    @SerializedName("color")
    private String color;
    @SerializedName("order")
    private String order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

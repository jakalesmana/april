package com.trusindo.april.model;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;
import com.trusindo.april.R;

/**
 * Created by jakaputra on 20/01/18.
 */

public class SurveyGroundData {

    @SerializedName("front_side")
    private String frontSide;

    @SerializedName("back_side")
    private String backSide;

    @SerializedName("left_side")
    private String leftSide;

    @SerializedName("right_side")
    private String rightSide;

    @SerializedName("elevation_to_road_front")
    private String elevationToRoadFront;

    @SerializedName("ground_contour")
    private String groundContour;

    @SerializedName("ground_type")
    private String groundType;

    @SerializedName("ground_position")
    private String groundPosition;

    @SerializedName("ground_orientation")
    private String groundOrientation;

    @SerializedName("ground_shape")
    private String groundShape;

    @SerializedName("ground_status")
    private String groundStatus;


    public String getFrontSide() {
        return frontSide;
    }

    public void setFrontSide(String frontSide) {
        this.frontSide = frontSide;
    }

    public String getBackSide() {
        return backSide;
    }

    public void setBackSide(String backSide) {
        this.backSide = backSide;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(String leftSide) {
        this.leftSide = leftSide;
    }

    public String getRightSide() {
        return rightSide;
    }

    public void setRightSide(String rightSide) {
        this.rightSide = rightSide;
    }

    public String getElevationToRoadFront() {
        return elevationToRoadFront;
    }

    public void setElevationToRoadFront(String elevationToRoadFront) {
        this.elevationToRoadFront = elevationToRoadFront;
    }

    public String getGroundContour() {
        return groundContour;
    }

    public void setGroundContour(String groundContour) {
        this.groundContour = groundContour;
    }

    public String getGroundType() {
        return groundType;
    }

    public void setGroundType(String groundType) {
        this.groundType = groundType;
    }

    public String getGroundPosition() {
        return groundPosition;
    }

    public void setGroundPosition(String groundPosition) {
        this.groundPosition = groundPosition;
    }

    public String getGroundOrientation() {
        return groundOrientation;
    }

    public void setGroundOrientation(String groundOrientation) {
        this.groundOrientation = groundOrientation;
    }

    public String getGroundShape() {
        return groundShape;
    }

    public void setGroundShape(String groundShape) {
        this.groundShape = groundShape;
    }

    public String getGroundStatus() {
        return groundStatus;
    }

    public void setGroundStatus(String groundStatus) {
        this.groundStatus = groundStatus;
    }
}

package com.trusindo.april.model;

import com.google.gson.annotations.SerializedName;

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


}

package com.trusindo.april.model;

import com.google.gson.annotations.SerializedName;
import com.trusindo.april.R;

/**
 * Created by jakaputra on 17/02/18.
 */

public class SurveyLocationData {

    @SerializedName("address")
    private String address;

    @SerializedName("sub_district")
    private String subDistrict;

    @SerializedName("district")
    private String district;

    @SerializedName("city")
    private String city;

    @SerializedName("province")
    private String province;

    @SerializedName("zip_code")
    private String zipCode;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

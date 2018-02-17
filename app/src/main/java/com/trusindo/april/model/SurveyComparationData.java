package com.trusindo.april.model;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;
import com.trusindo.april.R;

/**
 * Created by jakaputra on 17/02/18.
 */

public class SurveyComparationData {

    /**
     * Should be list of objects
     */

    @SerializedName("address")
    private String address;

    @SerializedName("sub_district")
    private String subDistrict;

    @SerializedName("district")
    private String district;

    @SerializedName("city")
    private String city;

    @SerializedName("contact_person")
    private String contactPerson;

    @SerializedName("phone")
    private String phone;

    @SerializedName("data_status")
    private String dataStatus;

    @SerializedName("price")
    private String price;

    @SerializedName("property_type")
    private String propertyType;

    @SerializedName("ground_area_size")
    private String groundAreaSize;

    @SerializedName("used_for")
    private String usedFor;

    @SerializedName("license")
    private String license;

    @SerializedName("access_width")
    private String accessWidth;

    @SerializedName("ground_position")
    private String groundPosition;

    @SerializedName("ground_contur")
    private String groundContur;

    @SerializedName("ground_shape")
    private String groundShape;

    @SerializedName("building_condition")
    private String buildingCondition;

    @SerializedName("building_size")
    private String buildingSize;

    @SerializedName("building_class")
    private String buildingClass;

    @SerializedName("floor_total")
    private String floorTotal;


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

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getGroundAreaSize() {
        return groundAreaSize;
    }

    public void setGroundAreaSize(String groundAreaSize) {
        this.groundAreaSize = groundAreaSize;
    }

    public String getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getAccessWidth() {
        return accessWidth;
    }

    public void setAccessWidth(String accessWidth) {
        this.accessWidth = accessWidth;
    }

    public String getGroundPosition() {
        return groundPosition;
    }

    public void setGroundPosition(String groundPosition) {
        this.groundPosition = groundPosition;
    }

    public String getGroundContur() {
        return groundContur;
    }

    public void setGroundContur(String groundContur) {
        this.groundContur = groundContur;
    }

    public String getGroundShape() {
        return groundShape;
    }

    public void setGroundShape(String groundShape) {
        this.groundShape = groundShape;
    }

    public String getBuildingCondition() {
        return buildingCondition;
    }

    public void setBuildingCondition(String buildingCondition) {
        this.buildingCondition = buildingCondition;
    }

    public String getBuildingSize() {
        return buildingSize;
    }

    public void setBuildingSize(String buildingSize) {
        this.buildingSize = buildingSize;
    }

    public String getBuildingClass() {
        return buildingClass;
    }

    public void setBuildingClass(String buildingClass) {
        this.buildingClass = buildingClass;
    }

    public String getFloorTotal() {
        return floorTotal;
    }

    public void setFloorTotal(String floorTotal) {
        this.floorTotal = floorTotal;
    }
}

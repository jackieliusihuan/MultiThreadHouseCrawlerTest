package cn.focus.house.model;

import java.util.List;

public class HouseSource {

    private String houseUrl;
    private String buildingName;
    private String buildingTitle;
    private double totalPrice;
    private int unitPrice;
    private double area;
    private String houseType;
    private String orientation;
    private String floor;
    private String buildingTags;
    private String decoration;
    private String buildingAge;
    private String companyName;
    private String buildingNum;
    private List<Review> reviews;

    public String getHouseUrl() {
        return houseUrl;
    }

    public void setHouseUrl(String houseUrl) {
        this.houseUrl = houseUrl;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingTitle() {
        return buildingTitle;
    }

    public void setBuildingTitle(String buildingTitle) {
        this.buildingTitle = buildingTitle;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuildingTags() {
        return buildingTags;
    }

    public void setBuildingTags(String buildingTags) {
        this.buildingTags = buildingTags;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getBuildingAge() {
        return buildingAge;
    }

    public void setBuildingAge(String buildingAge) {
        this.buildingAge = buildingAge;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
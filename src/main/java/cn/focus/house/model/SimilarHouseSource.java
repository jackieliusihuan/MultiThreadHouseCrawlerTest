package cn.focus.house.model;

import java.util.ArrayList;
import java.util.List;

public class SimilarHouseSource {
    HouseSource houseSource;
    List<HouseSource> similarHouseList;
    private int size=0;
    
    
    public HouseSource getHouseSource() {
        return houseSource;
    }
    public void setHouseSource(HouseSource houseSource) {
        this.houseSource = houseSource;
    }
    public List<HouseSource> getSimilarHouseList() {
        if(similarHouseList == null){
            similarHouseList = new ArrayList<HouseSource>();
        }
        return similarHouseList;
    }
    public void setSimilarHouseList(List<HouseSource> similarHouseList) {
        this.similarHouseList = similarHouseList;
    }
    
    public SimilarHouseSource(HouseSource houseSource, List<HouseSource> similarHouseList) {
        this.houseSource = houseSource;
        this.similarHouseList = similarHouseList;
    }
    public SimilarHouseSource() {
    }
    public SimilarHouseSource(HouseSource houseSource) {
        this.houseSource = houseSource;
        this.size = 1;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    
}

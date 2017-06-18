package cn.focus.house.threads.house;

import java.util.List;

import cn.focus.house.model.HouseSource;

public abstract class HouseBaseWorker implements Runnable {

    private List<String> houseList;
    
    public HouseBaseWorker(List<String> houseList) {
        this.houseList = houseList;
    }
    @Override
    public void run() {
        for(String houseUrl : houseList){
            HouseSource houseSource = crawlJob(houseUrl, 5000);
            if(houseSource != null){
                saveHouse(houseSource);
            }
        }
    }
    
    public abstract HouseSource crawlJob(String houseUrl, int timeout);
    public abstract void saveHouse(HouseSource houseSource);
}

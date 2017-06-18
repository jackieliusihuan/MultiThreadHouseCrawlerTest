package cn.focus.house.threads.house;

import java.util.List;


public interface HouseWorkerFactory {

    public HouseBaseWorker getWorker(List<String> houseList);

}

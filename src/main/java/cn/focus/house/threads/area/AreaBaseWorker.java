package cn.focus.house.threads.area;

import java.util.ArrayList;
import java.util.List;

public abstract class AreaBaseWorker implements Runnable {
    
    private List<String> destList;//注意这个List是多线程调用
    private List<String> srcList;

    @Override
    public void run() {
        List<String> tempDestList = new ArrayList<String>();
        for(String src : srcList){
            List<String> listTemp = crawlJob(src, 5000);
            if(listTemp != null &&listTemp.size() != 0){
                tempDestList.addAll(listTemp);
            }
        }
        if(tempDestList.size() != 0){
            destList.addAll(tempDestList);
        }
    }
    
    public abstract List<String> crawlJob(String url, int timeout) ;

    public List<String> getDestList() {
        return destList;
    }

    public void setDestList(List<String> destList) {
        this.destList = destList;
    }

    public List<String> getSrcList() {
        return srcList;
    }

    public void setSrcList(List<String> srcList) {
        this.srcList = srcList;
    }
    
}

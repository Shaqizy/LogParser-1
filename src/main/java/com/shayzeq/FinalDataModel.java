package com.shayzeq;

public class FinalDataModel {

     private String methodName;
     private String appkId;
     private double avgWorkTime;
     private long maxWorkTime;
     private long minWorkTime;

    public FinalDataModel(String methodName, String appkId, double avgWorkTime, long maxWorkTime, long minWorkTime) {
        this.methodName = methodName;
        this.appkId = appkId;
        this.avgWorkTime = avgWorkTime;
        this.maxWorkTime = maxWorkTime;
        this.minWorkTime = minWorkTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getAppkId() {
        return appkId;
    }

    public void setAppkId(String appkId) {
        this.appkId = appkId;
    }

    public double getAvgWorkTime() {
        return avgWorkTime;
    }

    public void setAvgWorkTime(double avgWorkTime) {
        this.avgWorkTime = avgWorkTime;
    }

    public long getMaxWorkTime() {
        return maxWorkTime;
    }

    public void setMaxWorkTime(long maxWorkTime) {
        this.maxWorkTime = maxWorkTime;
    }

    public long getMinWorkTime() {
        return minWorkTime;
    }

    public void setMinWorkTime(long minWorkTime) {
        this.minWorkTime = minWorkTime;
    }
}

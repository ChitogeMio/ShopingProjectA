package com.example.shopingprojecta.models;

public class WorkerUser {

    private String nameWorker;
    private String dayWork;
    private String timeStarWork;
    private String timeWork;
    private String totalTime;

    public WorkerUser() {
        // mac dinh cua firebaseData (neu khong co se cosloi tai du lieu ve)
    }

    public WorkerUser(String nameWorker, String dayWork, String timeStarWork, String timeWork, String totalTime) {
        this.nameWorker = nameWorker;
        this.dayWork = dayWork;
        this.timeStarWork = timeStarWork;
        this.timeWork = timeWork;
        this.totalTime = totalTime;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameWorker(String nameWorker) {
        this.nameWorker = nameWorker;
    }

    public String getDayWork() {
        return dayWork;
    }

    public void setDayWork(String dayWork) {
        this.dayWork = dayWork;
    }

    public String getTimeStarWork() {
        return timeStarWork;
    }

    public void setTimeStarWork(String timeStarWork) {
        this.timeStarWork = timeStarWork;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "WorkerUser{" +
                "nameWorker='" + nameWorker + '\'' +
                ", dayWork='" + dayWork + '\'' +
                ", timeStarWork='" + timeStarWork + '\'' +
                ", timeWork='" + timeWork + '\'' +
                ", totalTime='" + totalTime + '\'' +
                '}';
    }
}

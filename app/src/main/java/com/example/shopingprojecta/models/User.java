package com.example.shopingprojecta.models;

import java.util.Objects;

public class User {

    private String nameUser;
    private String diaChi;
    private String yOfB;
    private String sex;
    private String userIID;
    private String nameEmail;
    private String displayNameEmail;
    private String uidEmail;


    public User(){
        // mac dinh cua firebaseData (neu khong co se cosloi tai du lieu ve)
    }

    public User(String nameUser, String diaChi, String yOfB, String sex, String userIID, String nameEmail, String displayNameEmail, String uidEmail) {
        this.nameUser = nameUser;
        this.diaChi = diaChi;
        this.yOfB = yOfB;
        this.sex = sex;
        this.userIID = userIID;
        this.nameEmail = nameEmail;
        this.displayNameEmail = displayNameEmail;
        this.uidEmail = uidEmail;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getyOfB() {
        return yOfB;
    }

    public void setyOfB(String yOfB) {
        this.yOfB = yOfB;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserIID() {
        return userIID;
    }

    public void setUserIID(String userIID) {
        this.userIID = userIID;
    }

    public String getNameEmail() {
        return nameEmail;
    }

    public void setNameEmail(String nameEmail) {
        this.nameEmail = nameEmail;
    }

    public String getDisplayNameEmail() {
        return displayNameEmail;
    }

    public void setDisplayNameEmail(String displayNameEmail) {
        this.displayNameEmail = displayNameEmail;
    }

    public String getUidEmail() {
        return uidEmail;
    }

    public void setUidEmail(String uidEmail) {
        this.uidEmail = uidEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "nameUser='" + nameUser + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", yOfB='" + yOfB + '\'' +
                ", sex='" + sex + '\'' +
                ", userIID='" + userIID + '\'' +
                ", nameEmail='" + nameEmail + '\'' +
                ", displayNameEmail='" + displayNameEmail + '\'' +
                ", uidEmail='" + uidEmail + '\'' +
                '}';
    }
}

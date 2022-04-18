package com.example.shopingprojecta.models;

import java.util.Objects;

public class User {

    private String nameUser;
    private String diaChi;
    private String yOfB;
    private String sex;

    public User(){
        // mac dinh cua firebaseData (neu khong co se cosloi tai du lieu ve)
    }

    public User(String nameUser, String diaChi, String yOfB, String sex) {
        this.nameUser = nameUser;
        this.diaChi = diaChi;
        this.yOfB = yOfB;
        this.sex = sex;
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

    @Override
    public String toString() {
        return "User{" +
                "nameUser='" + nameUser + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", yOfB='" + yOfB + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

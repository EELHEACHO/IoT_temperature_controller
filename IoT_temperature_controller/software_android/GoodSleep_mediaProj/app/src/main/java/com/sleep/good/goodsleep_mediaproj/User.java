package com.sleep.good.goodsleep_mediaproj;

import android.util.Log;

/**
 * Created by eelhea on 2016-12-04.
 */

public class User {
    private String name = null;
    private String address = null;
    private String floorModelNum = null;
    private String roomModelNum = null;

    private User(){}

    private static class Singleton {
        private static final User user = new User();
    }

    public static User getInstance() {
        Log.e("development", "create singleton instance : User");
        return Singleton.user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFloorModelNum() {
        return floorModelNum;
    }

    public void setFloorModelNum(String floorModelNum) {
        this.floorModelNum = floorModelNum;
    }

    public String getRoomModelNum() {
        return roomModelNum;
    }

    public void setRoomModelNum(String roomModelNum) {
        this.roomModelNum = roomModelNum;
    }
}

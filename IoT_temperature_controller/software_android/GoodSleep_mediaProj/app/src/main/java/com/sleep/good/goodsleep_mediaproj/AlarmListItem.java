package com.sleep.good.goodsleep_mediaproj;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

/**
 * Created by eelhea on 2016-12-05.
 */

public class AlarmListItem {
    private ImageButton deleteIcon;
    private String ampm;
    private String time;
    private boolean sun;
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thurs;
    private boolean fri;
    private boolean sat;
    private boolean switch_on_off;

    public AlarmListItem(ImageButton deleteIcon, String ampm, String time, boolean sun, boolean mon, boolean tue, boolean wed, boolean thurs, boolean fri, boolean sat,boolean switch_on_off ){
        this.deleteIcon = deleteIcon;
        this.ampm = ampm;
        this.time = time;
        this.sun = sun;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thurs = thurs;
        this.fri = fri;
        this.sat = sat;
        this.switch_on_off = switch_on_off;
    }

    public ImageButton getDeleteIcon() {
        return deleteIcon;
    }

    public void setDeleteIcon(ImageButton deleteIcon) {
        this.deleteIcon = deleteIcon;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThurs() {
        return thurs;
    }

    public void setThurs(boolean thurs) {
        this.thurs = thurs;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSwitch_on_off() {
        return switch_on_off;
    }

    public void setSwitch_on_off(boolean switch_on_off) {
        this.switch_on_off = switch_on_off;
    }
}

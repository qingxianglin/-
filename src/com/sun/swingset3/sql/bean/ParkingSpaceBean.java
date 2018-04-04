package com.sun.swingset3.sql.bean;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceBean {
    private Integer parkingLotId;

    private List<Integer> parkingSpaceCoords = new ArrayList<Integer>();

    private List<Integer> usedSpaceCoords = new ArrayList<Integer>();

    private List<Integer> entryCoords = new ArrayList<Integer>();

    private List<Integer> exitCoords = new ArrayList<Integer>();

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public List<Integer> getParkingSpaceCoords() {
        return parkingSpaceCoords;
    }

    public void setParkingSpaceCoords(List<Integer> parkingSpaceCoords) {
        this.parkingSpaceCoords = parkingSpaceCoords;
    }

    public List<Integer> getUsedSpaceCoords() {
        return usedSpaceCoords;
    }

    public void setUsedSpaceCoords(List<Integer> usedSpaceCoords) {
        this.usedSpaceCoords = usedSpaceCoords;
    }

    public List<Integer> getEntryCoords() {
        return entryCoords;
    }

    public void setEntryCoords(List<Integer> entryCoords) {
        this.entryCoords = entryCoords;
    }

    public List<Integer> getExitCoords() {
        return exitCoords;
    }

    public void setExitCoords(List<Integer> exitCoords) {
        this.exitCoords = exitCoords;
    }
}

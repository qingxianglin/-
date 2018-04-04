package com.sun.swingset3.sql.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ParkingLotBean {

    private String managerName;

    private Integer count;

    private String address;

    private List<ManagerBean> managerBeanList = new ArrayList<>();

    private Integer parkingLotId;

    private Date createdTime;

    private BigDecimal chargeStandard;

    List<Integer> selectedParkingSpace = new LinkedList<Integer>();

    List<Integer> selectedEntry = new LinkedList<Integer>();

    List<Integer> selectedExit = new LinkedList<Integer>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ManagerBean> getManagerBeanList() {
        return managerBeanList;
    }

    public void setManagerBeanList(List<ManagerBean> managerBeanList) {
        this.managerBeanList = managerBeanList;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public BigDecimal getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(BigDecimal chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    public List<Integer> getSelectedParkingSpace() {
        return selectedParkingSpace;
    }

    public void setSelectedParkingSpace(List<Integer> selectedParkingSpace) {
        this.selectedParkingSpace = selectedParkingSpace;
    }

    public List<Integer> getSelectedEntry() {
        return selectedEntry;
    }

    public void setSelectedEntry(List<Integer> selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    public List<Integer> getSelectedExit() {
        return selectedExit;
    }

    public void setSelectedExit(List<Integer> selectedExit) {
        this.selectedExit = selectedExit;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}

package com.sun.swingset3.sql.bean;

import java.math.BigDecimal;
import java.util.Date;

//车辆入场基本信息
public class CarInBean {
    private BigDecimal cost;

    private Integer logId;

    private Integer cardId;

    private String cardType;

    private Date carInTime;

    private Date carOutTime;

    private String carInPicturePath;

    private String carOutPicturePath;

    private String address;

    private Integer parkingLotId;

    private String managerName;

    private Integer managerId;

    private String carNo = "";

    private Integer stopNo;

    private BigDecimal costStandard;

    public Date getCarInTime() {
        return carInTime;
    }

    public void setCarInTime(Date carInTime) {
        this.carInTime = carInTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getStopNo() {
        return stopNo;
    }

    public void setStopNo(Integer stopNo) {
        this.stopNo = stopNo;
    }

    public String getCarInPicturePath() {
        return carInPicturePath;
    }

    public void setCarInPicturePath(String carInPicturePath) {
        this.carInPicturePath = carInPicturePath;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCarOutPicturePath() {
        return carOutPicturePath;
    }

    public void setCarOutPicturePath(String carOutPicturePath) {
        this.carOutPicturePath = carOutPicturePath;
    }

    public Date getCarOutTime() {
        return carOutTime;
    }

    public void setCarOutTime(Date carOutTime) {
        this.carOutTime = carOutTime;
    }

    public BigDecimal getCostStandard() {
        return costStandard;
    }

    public void setCostStandard(BigDecimal costStandard) {
        this.costStandard = costStandard;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}

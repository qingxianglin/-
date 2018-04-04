package com.sun.swingset3.sql.bean;

public class ManagerBean {

    private Integer managerId;

    private String managerName;

    private String role;

    public ManagerBean(Integer managerId, String managerName, String role) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.role = role;
    }

    public ManagerBean() {

    }

    public static final String CHARGER= "CHARGER";

    public static final String ADMIN = "ADMIN";

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

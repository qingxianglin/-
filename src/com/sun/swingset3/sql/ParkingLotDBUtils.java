package com.sun.swingset3.sql;

import com.mysql.jdbc.StringUtils;
import com.sun.swingset3.sql.bean.*;

import javax.management.relation.Role;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ParkingLotDBUtils extends DBUtilBase {

    public ParkingLotDBUtils() {
        super();
    }

    //根据收费员工号查询车辆入场基本信息
    public CarInBean queryCarInBean(Integer managerId){
        CarInBean carInBean = new CarInBean();
        String sql = "SELECT * FROM manager_parkinglot_relation where manager_id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,managerId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                carInBean.setManagerId(managerId);
                carInBean.setParkingLotId(rs.getInt("parkinglot_id"));
                sql = "SELECT * FROM parkinglot_info where id = ?";
                pstmt = getStatement(conn,sql);
                pstmt.setInt(1,carInBean.getParkingLotId());
                rs = pstmt.executeQuery();
                if(rs.next()){
                    carInBean.setAddress(rs.getString("address"));
                    carInBean.setCostStandard(rs.getBigDecimal("charge_standard"));
                    //carInBean.setCarInTime(new Date());
                    sql = "SELECT * FROM manager_info where id = ?";
                    pstmt = getStatement(conn,sql);
                    pstmt.setInt(1,carInBean.getManagerId());
                    rs = pstmt.executeQuery();
                    if(rs.next()){
                        carInBean.setManagerName(rs.getString("name"));
                    }
                }
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){

        }
        return carInBean;
    }

    //根据姓名查询管理员信息
    public List<ManagerBean> queryManagerBean(String keyword){
        List<ManagerBean> result = new ArrayList<>();
        String sql = "SELECT id,name from manager_info where name like ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,"%"+keyword+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                ManagerBean managerBean = new ManagerBean();
                managerBean.setManagerId(rs.getInt("id"));
                managerBean.setManagerName(rs.getString("name"));
                result.add(managerBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){

        }
        return result;
    }

    //车辆进出记录数目查询
    public Integer queryCarInBeanCount(CarInBean queryBean){
        String sql = "SELECT count(*) from parking_log where leave_time is not null";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){

        }
        return 0;
    }

    public Integer queryAuthReqCount(){
        String sql = "SELECT count(*) from tb_user_role where status !=?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,"等待审核");
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){

        }
        return 0;
    }

    public Integer queryAuthReqCount2(String status){
        String sql = "SELECT count(*) from tb_user_role where status = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,status);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){

        }
        return 0;
    }

    public Integer queryCardCount(CardBean cardBean){
        String sql = "SELECT count(*) FROM card where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(cardBean.getName())){
            sql+=" and name=? ";
            params.add(cardBean.getName());
        }
        if(cardBean.getCardId()!=null){
            sql+=" and id=? ";
            params.add(cardBean.getCardId());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getPhone())){
            sql+=" and phone=? ";
            params.add(cardBean.getPhone());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getCardType())){
            sql+=" and type=? ";
            params.add(cardBean.getCardType());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getStatus())){
            sql+=" and status=? ";
            params.add(cardBean.getStatus());
        }
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //车辆进出记录查询
    public List<CarInBean> queryCarInBean(CarInBean queryBean,int start,int end){
        List<CarInBean> result = new ArrayList<>();
        String sql = "select t1.id,t1.fee,t1.enter_time,t1.leave_time,t1.plate_number,t1.enter_photo_url,t1.leave_photo_url,t1.parkingspace_coord,t2.address,t3.name " +
                "from parking_log as t1," +
                "parkinglot_info as t2," +
                "manager_info as t3 " +
                "where t1.manager_id = t3.id and t1.parkinglot_id = t2.id and t1.leave_time is not null limit ?,?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,start);
            pstmt.setInt(2,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CarInBean carInBean = new CarInBean();
                carInBean.setLogId(rs.getInt("id"));
                carInBean.setAddress(rs.getString("address"));
                carInBean.setManagerName(rs.getString("name"));
                carInBean.setCarInTime(rs.getTimestamp("enter_time"));
                carInBean.setCarOutTime(rs.getTimestamp("leave_time"));
                carInBean.setCarNo(rs.getString("plate_number"));
                carInBean.setCarInPicturePath(rs.getString("enter_photo_url"));
                carInBean.setCarOutPicturePath(rs.getString("leave_photo_url"));
                carInBean.setStopNo(rs.getInt("parkingspace_coord"));
                carInBean.setCost(rs.getBigDecimal("fee"));
                result.add(carInBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //停车场查询
    public List<ParkingLotBean> queryParkingLotBean(ParkingLotBean queryBean,int start,int end){
        List<ParkingLotBean> result = new ArrayList<>();
        String sql = "SELECT t1.id, t1.created_time,t1.parkingspace_coord,t1.address,t2.name,t1.charge_standard from parkinglot_info as t1,manager_info as t2, manager_parkinglot_relation as t3 WHERE " +
                "t1.id = t3.parkinglot_id and t2.id = t3.manager_id limit ?,?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,start);
            pstmt.setInt(2,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                ParkingLotBean bean = new ParkingLotBean();
                bean.setParkingLotId(rs.getInt("id"));
                bean.setAddress(rs.getString("address"));
                bean.setManagerName(rs.getString("name"));
                bean.setChargeStandard(rs.getBigDecimal("charge_standard"));
                bean.setCount(rs.getString("parkingspace_coord").split(",").length);
                bean.setCreatedTime(rs.getTimestamp("created_time"));
                result.add(bean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //停车场数目查询
    public Integer queryParkingLotCount(ParkingLotBean queryBean){
        String sql = "SELECT count(*) from parkinglot_info";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){

        }
        return 0;
    }

    //查询停车场车位信息
    public ParkingSpaceBean queryParkingSpaceBean(Integer parkingLotId){
        ParkingSpaceBean spaceBean = new ParkingSpaceBean();
        String sql = "SELECT * FROM parkinglot_info where id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,parkingLotId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                spaceBean.setParkingLotId(parkingLotId);
                String[] spaceCoordStr = rs.getString("parkingspace_coord").split(",");
                List<Integer> spaceCoordList = new ArrayList<Integer>();
                for(String coord : spaceCoordStr){
                    spaceCoordList.add(Integer.parseInt(coord));
                }
                spaceBean.setParkingSpaceCoords(spaceCoordList);

                String[] entryCoordStr = rs.getString("entry_coord").split(",");
                List<Integer> entryCoordList = new ArrayList<Integer>();
                for(String coord : entryCoordStr){
                    entryCoordList.add(Integer.parseInt(coord));
                }
                spaceBean.setEntryCoords(entryCoordList);

                String[] exitCoordStr = rs.getString("exit_coord").split(",");
                List<Integer> exitCoordList = new ArrayList<Integer>();
                for(String coord : exitCoordStr){
                    exitCoordList.add(Integer.parseInt(coord));
                }
                spaceBean.setExitCoords(exitCoordList);

                sql = "SELECT * FROM parking_log where parkinglot_id = ? and leave_time is null";
                pstmt = getStatement(conn,sql);
                pstmt.setInt(1,parkingLotId);
                rs = pstmt.executeQuery();
                List<Integer> usedCoordList = new ArrayList<Integer>();
                while(rs.next()){
                    usedCoordList.add(rs.getInt("parkingspace_coord"));
                }
                spaceBean.setUsedSpaceCoords(usedCoordList);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return spaceBean;
    }

    //新建停车场
    public void createParkingLot(ParkingLotBean parkingLotBean){
        String sql = "INSERT INTO parkinglot_info(creator, created_time, modifier, " +
                "modified_time, address, charge_standard, parkingspace_coord, " +
                "entry_coord, exit_coord) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,"admin");
            pstmt.setDate(2,new java.sql.Date(new Date().getTime()));
            pstmt.setString(3,"admin");
            pstmt.setDate(4,new java.sql.Date(new Date().getTime()));
            pstmt.setString(5,parkingLotBean.getAddress());
            pstmt.setBigDecimal(6,parkingLotBean.getChargeStandard());
            pstmt.setString(7,list2Str(parkingLotBean.getSelectedParkingSpace()));
            pstmt.setString(8,list2Str(parkingLotBean.getSelectedEntry()));
            pstmt.setString(9,list2Str(parkingLotBean.getSelectedExit()));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                Integer parkingLotId = rs.getInt(1);
                parkingLotBean.setParkingLotId(parkingLotId);
            }
            sql = "INSERT INTO manager_parkinglot_relation " +
                    "(creator, created_time, modifier, modified_time, manager_role, manager_id, parkinglot_id) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?)";
            pstmt = getStatement(conn,sql);
            for(int i = 0;i<parkingLotBean.getManagerBeanList().size();++i){
                pstmt.setString(1,"admin");
                pstmt.setDate(2,new java.sql.Date(new Date().getTime()));
                pstmt.setString(3,"admin");
                pstmt.setDate(4,new java.sql.Date(new Date().getTime()));
                pstmt.setString(5,parkingLotBean.getManagerBeanList().get(i).getRole());
                pstmt.setInt(6,parkingLotBean.getManagerBeanList().get(i).getManagerId());
                pstmt.setInt(7,parkingLotBean.getParkingLotId());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            cleanUp(null,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //插入车辆入场记录
    public void createCarInLog(CarInBean carInBean){
        String sql = "INSERT INTO parking_log(enter_time, manager_id, plate_number, enter_photo_url, " +
                "parkinglot_id, parkingspace_coord, card_id" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setTimestamp(1,new Timestamp(carInBean.getCarInTime().getTime()));
            pstmt.setInt (2,carInBean.getManagerId());
            pstmt.setString(3,carInBean.getCarNo());
            pstmt.setString(4,carInBean.getCarInPicturePath());
            pstmt.setInt(5,carInBean.getParkingLotId());
            pstmt.setInt(6,carInBean.getStopNo());
            pstmt.setInt(7,carInBean.getCardId());
            pstmt.executeUpdate();
            cleanUp(null,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //查询IC卡号
    public List<CardBean> queryCardBeans(Integer cardId){
        List<CardBean> cardBeans = new ArrayList<CardBean>();
        String sql = "SELECT * FROM card where id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,cardId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CardBean cardBean = new CardBean();
                cardBean.setCardId(rs.getInt("id"));
                cardBeans.add(cardBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cardBeans;
    }

    //查询IC卡
    public List<CardBean> queryCard(CardBean cardBean){
        List<CardBean> cardBeans = new ArrayList<CardBean>();
        String sql = "SELECT * FROM card where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(cardBean.getName())){
            sql+=" and name=? ";
            params.add(cardBean.getName());
        }
        if(cardBean.getCardId()!=null){
            sql+=" and id=? ";
            params.add(cardBean.getCardId());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getPhone())){
            sql+=" and phone=? ";
            params.add(cardBean.getPhone());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getCardType())){
            sql+=" and type=? ";
            params.add(cardBean.getCardType());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getStatus())){
            sql+=" and status=? ";
            params.add(cardBean.getStatus());
        }
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CardBean card = new CardBean();
                card.setCardId(rs.getInt("id"));
                card.setStatus(rs.getString("status"));
                card.setPhone(rs.getString("phone"));
                card.setName(rs.getString("name"));
                card.setGender(rs.getString("gender"));
                card.setCardType(rs.getString("type"));
                card.setMoney(rs.getBigDecimal("money"));
                card.setExpireTime(rs.getTimestamp("expire_time"));
                card.setAvailable(rs.getInt("available"));
                cardBeans.add(card);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cardBeans;
    }

    public Integer queryAuthByConditionCount(AuthBean queryBean){
        String sql = "SELECT COUNT(*) FROM tb_auth where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(queryBean.getNameZh())){
            sql+=" and name_zh like ? ";
            params.add("%"+queryBean.getNameZh()+"%");
        }
        if(!StringUtils.isNullOrEmpty(queryBean.getNameEn())){
            sql+=" and name_en like ? ";
            params.add("%"+queryBean.getNameEn()+"%");
        }
        if(queryBean.getId()!=null){
            sql+=" and id=? ";
            params.add(queryBean.getId());
        }
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Integer queryRoleByConditionCount(RoleBean queryBean){
        String sql = "SELECT COUNT(*) FROM tb_role where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(queryBean.getNameZh())){
            sql+=" and name_zh like ? ";
            params.add("%"+queryBean.getNameZh()+"%");
        }
        if(!StringUtils.isNullOrEmpty(queryBean.getNameEn())){
            sql+=" and name_en like ? ";
            params.add("%"+queryBean.getNameEn()+"%");
        }
        if(queryBean.getId()!=null){
            sql+=" and id=? ";
            params.add(queryBean.getId());
        }
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<UserBean> queryUserByCondition(UserBean queryBean,int start,int end){
        List<UserBean> result = new ArrayList<UserBean>();
        String sql = "SELECT * FROM manager_info where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(queryBean.getName())){
            sql+=" and name like ? ";
            params.add("%"+queryBean.getName()+"%");
        }
        if(!StringUtils.isNullOrEmpty(queryBean.getSex())){
            sql+=" and sex=? ";
            params.add(queryBean.getSex());
        }
        if(queryBean.getId()!=null){
            sql+=" and id=? ";
            params.add(queryBean.getId());
        }
        sql+=" limit ?,?";
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            pstmt.setInt(params.size()+1,start);
            pstmt.setInt(params.size()+2,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                UserBean bean = new UserBean();
                bean.setId(rs.getInt("id"));
                bean.setName(rs.getString("name"));
                bean.setPath(rs.getString("path"));
                bean.setIdCard(rs.getString("id_card"));
                bean.setSex(rs.getString("sex"));
                bean.setPhonex(rs.getString("phone_number"));
                bean.setPicturePath(rs.getString("photo"));
                result.add(bean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Integer queryUserCountByCondition(UserBean queryBean){
        String sql = "SELECT COUNT(*) FROM manager_info where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(queryBean.getName())){
            sql+=" and name like ? ";
            params.add("%"+queryBean.getName()+"%");
        }
        if(!StringUtils.isNullOrEmpty(queryBean.getSex())){
            sql+=" and sex=? ";
            params.add(queryBean.getSex());
        }
        if(queryBean.getId()!=null){
            sql+=" and id=? ";
            params.add(queryBean.getId());
        }
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<RoleBean> queryRoleByCondition(RoleBean queryBean,int start,int end){
        List<RoleBean> result = new ArrayList<RoleBean>();
        String sql = "SELECT * FROM tb_role where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(queryBean.getNameZh())){
            sql+=" and name_zh like ? ";
            params.add("%"+queryBean.getNameZh()+"%");
        }
        if(!StringUtils.isNullOrEmpty(queryBean.getNameEn())){
            sql+=" and name_en like ? ";
            params.add("%"+queryBean.getNameEn()+"%");
        }
        if(queryBean.getId()!=null){
            sql+=" and id=? ";
            params.add(queryBean.getId());
        }
        sql+=" limit ?,?";
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            pstmt.setInt(params.size()+1,start);
            pstmt.setInt(params.size()+2,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                RoleBean bean = new RoleBean();
                bean.setId(rs.getInt("id"));
                bean.setNameZh(rs.getString("name_zh"));
                bean.setNameEn(rs.getString("name_en"));
                bean.setPath(rs.getString("path"));
                bean.setRemark(rs.getString("remark"));
                result.add(bean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<AuthBean> queryAuthByCondition(AuthBean queryBean,int start,int end){
        List<AuthBean> result = new ArrayList<AuthBean>();
        String sql = "SELECT * FROM tb_auth where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(queryBean.getNameZh())){
            sql+=" and name_zh like ? ";
            params.add("%"+queryBean.getNameZh()+"%");
        }
        if(!StringUtils.isNullOrEmpty(queryBean.getNameEn())){
            sql+=" and name_en like ? ";
            params.add("%"+queryBean.getNameEn()+"%");
        }
        if(queryBean.getId()!=null){
            sql+=" and id=? ";
            params.add(queryBean.getId());
        }
        sql+=" limit ?,?";
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            pstmt.setInt(params.size()+1,start);
            pstmt.setInt(params.size()+2,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AuthBean bean = new AuthBean();
                bean.setId(rs.getInt("id"));
                bean.setNameZh(rs.getString("name_zh"));
                bean.setNameEn(rs.getString("name_en"));
                bean.setPath(rs.getString("path"));
                bean.setRemark(rs.getString("remark"));
                result.add(bean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //查询IC卡
    public List<CardBean> queryCard(CardBean cardBean,int start,int end){
        List<CardBean> cardBeans = new ArrayList<CardBean>();
        String sql = "SELECT * FROM card where 1=1";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(cardBean.getName())){
            sql+=" and name=? ";
            params.add(cardBean.getName());
        }
        if(cardBean.getCardId()!=null){
            sql+=" and id=? ";
            params.add(cardBean.getCardId());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getPhone())){
            sql+=" and phone=? ";
            params.add(cardBean.getPhone());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getCardType())){
            sql+=" and type=? ";
            params.add(cardBean.getCardType());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getStatus())){
            sql+=" and status=? ";
            params.add(cardBean.getStatus());
        }
        sql+=" limit ?,?";
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            pstmt.setInt(params.size()+1,start);
            pstmt.setInt(params.size()+2,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                CardBean card = new CardBean();
                card.setCardId(rs.getInt("id"));
                card.setStatus(rs.getString("status"));
                card.setPhone(rs.getString("phone"));
                card.setName(rs.getString("name"));
                card.setGender(rs.getString("gender"));
                card.setCardType(rs.getString("type"));
                card.setMoney(rs.getBigDecimal("money"));
                card.setExpireTime(rs.getTimestamp("expire_time"));
                card.setAvailable(rs.getInt("available"));
                cardBeans.add(card);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cardBeans;
    }

    //更新IC卡
    public void updateCard(CardBean cardBean){
        String sql = "update card set ";
        Connection conn = getConnection();
        List<Object> params = new ArrayList<Object>();
        if(!StringUtils.isNullOrEmpty(cardBean.getName())){
            sql+="name=?,";
            params.add(cardBean.getName());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getPhone())){
            sql+="phone=?,";
            params.add(cardBean.getPhone());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getCardType())){
            sql+="type=?,";
            params.add(cardBean.getCardType());
        }
        if(!StringUtils.isNullOrEmpty(cardBean.getStatus())){
            sql+="status=?,";
            params.add(cardBean.getStatus());
        }
        if(cardBean.getMoney()!=null){
            sql+="money=?,";
            params.add(cardBean.getMoney());
        }
        sql = sql.substring(0,sql.lastIndexOf(','));
        sql+=" where id=?";
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<params.size();++i){
                setParamValue(pstmt,i+1,params.get(i));
            }
            pstmt.setInt(params.size()+1,cardBean.getCardId());
            pstmt.executeUpdate();
            cleanUp(null,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //发行IC卡
    public void createCard(CardBean cardBean){
        String sql = "INSERT INTO card(status, type, money," +
                " name, gender, password, " +
                "phone, expire_time, available) VALUES (?,?,?,?,?,?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,cardBean.getStatus());
            pstmt.setString(2,cardBean.getCardType());
            pstmt.setBigDecimal(3,cardBean.getMoney());
            pstmt.setString(4,cardBean.getName());
            pstmt.setString(5,cardBean.getGender());
            pstmt.setString(6,cardBean.getPassword());
            pstmt.setString(7,cardBean.getPhone());
            pstmt.setDate(8,new java.sql.Date(cardBean.getExpireTime().getTime()));
            pstmt.setInt(9,cardBean.getAvailable());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                cardBean.setCardId(rs.getInt(1));
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //查询车辆入场日志
    public void queryCarInLog(String carNo,CarInBean carInBean){
        String sql = "SELECT * FROM parking_log where plate_number = ? order by enter_time desc limit 1";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,carNo);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                carInBean.setCarNo(carNo);
                carInBean.setCardId(rs.getInt("card_id"));
                carInBean.setCarInPicturePath(rs.getString("enter_photo_url"));
                carInBean.setStopNo(rs.getInt("parkingspace_coord"));
                carInBean.setCarInTime(rs.getTimestamp("enter_time"));
                carInBean.setLogId(rs.getInt("id"));
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //插入车辆出场日志
    public void createCarOutLog(CarInBean carInBean){
        String sql = "UPDATE parking_log set leave_time = ?,leave_photo_url=? where id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setTimestamp(1,new Timestamp(carInBean.getCarOutTime().getTime()));
            pstmt.setString(2,carInBean.getCarOutPicturePath());
            pstmt.setInt(3,carInBean.getLogId());
            pstmt.executeUpdate();
            cleanUp(null,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //插入新权限
    public void createAuth(AuthBean authBean){
        String sql = "INSERT INTO tb_auth(name_zh, name_en, path, remark) VALUES (?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,authBean.getNameZh());
            pstmt.setString(2,authBean.getNameEn());
            pstmt.setString(3,authBean.getPath());
            pstmt.setString(4,authBean.getRemark());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                authBean.setId(rs.getInt(1));
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //插入角色
    public void createRole(RoleBean roleBean){
        String sql = "INSERT INTO tb_role(name_zh, name_en, path, remark) VALUES (?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,roleBean.getNameZh());
            pstmt.setString(2,roleBean.getNameEn());
            pstmt.setString(3,roleBean.getPath());
            pstmt.setString(4,roleBean.getRemark());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                roleBean.setId(rs.getInt(1));
            }
            sql = "INSERT INTO tb_role_auth(role_id, auth_id,authName_en,authName_zh) VALUES (?,?,?,?)";
            pstmt = getStatement(conn,sql);
            List<AuthBean> authBeans = roleBean.getAuthBeanList();
            if(authBeans!=null && authBeans.size()>0){
                for(AuthBean authBean : authBeans){
                    pstmt.setInt(1,roleBean.getId());
                    pstmt.setInt(2,authBean.getId());
                    pstmt.setString(3,authBean.getNameEn());
                    pstmt.setString(4,authBean.getNameZh());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //插入用户
    public void createUser(UserBean userBean){
        String sql = "INSERT INTO manager_info(name, id_card, sex, phone_number, " +
                "photo, date_birthday, password, account, path)" +
                " VALUES (?,?,?,?,?,?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,userBean.getName());
            pstmt.setString(2,userBean.getIdCard());
            pstmt.setString(3,userBean.getSex());
            pstmt.setString(4,userBean.getPhonex());
            pstmt.setString(5,userBean.getPicturePath());
            pstmt.setDate(6,new java.sql.Date(userBean.getBirthday().getTime()));
            pstmt.setString(7,userBean.getPassword());
            pstmt.setString(8,userBean.getAccount());
            pstmt.setString(9,userBean.getPath());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                userBean.setId(rs.getInt(1));
            }
            sql = "INSERT INTO tb_user_role(user_id, role_id,roleName_en,roleName_zh) VALUES (?,?,?,?)";
            pstmt = getStatement(conn,sql);
            List<RoleBean> roleBeans = userBean.getRoleBeanList();
            if(roleBeans!=null && roleBeans.size()>0){
                for(RoleBean roleBean : roleBeans){
                    pstmt.setInt(1,userBean.getId());
                    pstmt.setInt(2,roleBean.getId());
                    pstmt.setString(3,roleBean.getNameEn());
                    pstmt.setString(4,roleBean.getNameZh());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createAuthRequest(RoleBean roleBean,Integer userId){
        String sql = "INSERT INTO tb_user_role(user_id, role_id,roleName_en,roleName_zh,status) VALUES (?,?,?,?,?)";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
                pstmt.setInt(1,userId);
                pstmt.setInt(2,roleBean.getId());
                pstmt.setString(3,roleBean.getNameEn());
                pstmt.setString(4,roleBean.getNameZh());
                pstmt.setString(5,"等待审核");
                pstmt.executeUpdate();
                cleanUp(null,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //查询权限列表
    public List<AuthBean> queryAuthBean(String authName){
        List<AuthBean> authBeans = new ArrayList<AuthBean>();
        String sql = "SELECT * FROM tb_auth where name_zh like ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,"%"+authName+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AuthBean authBean = new AuthBean();
                authBean.setId(rs.getInt("id"));
                authBean.setNameEn(rs.getString("name_en"));
                authBean.setNameZh(rs.getString("name_zh"));
                authBean.setPath(rs.getString("path"));
                authBean.setRemark(rs.getString("remark"));
                authBeans.add(authBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return authBeans;
    }

    //查询权限列表
    public List<AuthBean> queryAuthBean2(String authName){
        List<AuthBean> authBeans = new ArrayList<AuthBean>();
        String sql = "SELECT * FROM tb_auth";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            //pstmt.setString(1,"%"+authName+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AuthBean authBean = new AuthBean();
                authBean.setId(rs.getInt("id"));
                authBean.setNameEn(rs.getString("name_en"));
                authBean.setNameZh(rs.getString("name_zh"));
                authBean.setPath(rs.getString("path"));
                authBean.setRemark(rs.getString("remark"));
                authBeans.add(authBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return authBeans;
    }

    //查询权限列表
    public List<RoleBean> queryRoleBean(String roleName){
        List<RoleBean> roleBeans = new ArrayList<RoleBean>();
        String sql = "SELECT * FROM tb_role where name_zh like ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,"%"+roleName+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                RoleBean roleBean = new RoleBean();
                roleBean.setId(rs.getInt("id"));
                roleBean.setNameEn(rs.getString("name_en"));
                roleBean.setNameZh(rs.getString("name_zh"));
                roleBean.setPath(rs.getString("path"));
                roleBean.setRemark(rs.getString("remark"));
                roleBeans.add(roleBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleBeans;
    }

    public List<RoleBean> queryRoleBean2(){
        List<RoleBean> roleBeans = new ArrayList<RoleBean>();
        String sql = "SELECT * FROM tb_role";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                RoleBean roleBean = new RoleBean();
                roleBean.setId(rs.getInt("id"));
                roleBean.setNameEn(rs.getString("name_en"));
                roleBean.setNameZh(rs.getString("name_zh"));
                roleBean.setPath(rs.getString("path"));
                roleBean.setRemark(rs.getString("remark"));
                roleBeans.add(roleBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleBeans;
    }

    //查询用户列表
    public List<UserBean> queryUserBean(String userName){
        List<UserBean> userBeans = new ArrayList<UserBean>();
        String sql = "SELECT * FROM manager_info";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            if(userName!=null){
                sql = sql + "where name like ?";
                pstmt.setString(1,"%"+userName+"%");
            }
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                UserBean userBean = new UserBean();
                userBean.setId(rs.getInt("id"));
                userBean.setName(rs.getString("name"));
                userBean.setIdCard(rs.getString("id_card"));
                userBean.setSex(rs.getString("sex"));
                userBean.setPhonex(rs.getString("phone_number"));
                userBean.setPicturePath(rs.getString("photo"));
                userBean.setBirthday(rs.getDate("date_birthday"));
                userBean.setPassword(rs.getString("password"));
                userBean.setAccount(rs.getInt("id")+"");
                userBean.setPath(rs.getString("path"));
                userBeans.add(userBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userBeans;
    }

    //查询角色关联的权限列表
    public Vector<AuthBean> queryRoleAuthBean(Integer roleId){
        Vector<AuthBean> authBeans = new Vector<AuthBean>();
        String sql = "SELECT * FROM tb_role_auth where role_id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,roleId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AuthBean authBean = new AuthBean();
                authBean.setId(rs.getInt("auth_id"));
                authBean.setNameEn(rs.getString("authName_en"));
                authBean.setNameZh(rs.getString("authName_zh"));
                authBeans.add(authBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return authBeans;
    }

    //TODO
    public Vector<RoleBean> queryAuthRoleBean(String authName){
        Vector<RoleBean> roleBeans = new Vector<RoleBean>();
        String sql = "select t2.role_id,t1.name_en,t1.name_zh,t2.auth_id,t2.authName_en,t2.authName_zh from tb_role as t1,tb_role_auth as t2 where t1.id = t2.role_id and t2.authName_en=?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,authName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                RoleBean roleBean = new RoleBean();
                roleBean.setId(rs.getInt("role_id"));
                roleBean.setNameEn(rs.getString("name_en"));
                roleBean.setNameZh(rs.getString("name_zh"));
                roleBeans.add(roleBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleBeans;
    }

    //查询角色关联的权限列表
    public Vector<AuthBean> queryRoleAuthBean(List<RoleBean> roleBeans){
        Vector<AuthBean> authBeans = new Vector<AuthBean>();
        String sql = "SELECT * FROM tb_role_auth where role_id in (";
        for(int i = 0;i<roleBeans.size();++i){
            sql+="?";
            if(i!=roleBeans.size()-1){
                sql+=",";
            }
        }
        sql+=")";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<roleBeans.size();++i){
                setParamValue(pstmt,i+1,roleBeans.get(i).getId());
            }
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AuthBean authBean = new AuthBean();
                authBean.setId(rs.getInt("auth_id"));
                authBean.setNameEn(rs.getString("authName_en"));
                authBean.setNameZh(rs.getString("authName_zh"));
                authBeans.add(authBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return authBeans;
    }

    //查询用户关联的角色列表
    public Vector<RoleBean> queryUserRoleBean(Integer userId){
        Vector<RoleBean> roleBeans = new Vector<RoleBean>();
        String sql = "SELECT * FROM tb_user_role where user_id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,userId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                RoleBean roleBean = new RoleBean();
                roleBean.setId(rs.getInt("role_id"));
                roleBean.setNameEn(rs.getString("roleName_en"));
                roleBean.setNameZh(rs.getString("roleName_zh"));
                roleBeans.add(roleBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleBeans;
    }

    public List<AuthReqBean> queryAuthReqBean(AuthReqBean queryBean,int start,int end){
        List<AuthReqBean> result = new ArrayList<AuthReqBean>();
        String sql = "SELECT t1.status,t1.id,t1.user_id,t1.role_id,t1.roleName_en,t1.roleName_zh," +
                "t2.name from tb_user_role as t1," +
                "manager_info as t2 where t1.user_id = t2.id and t1.status=? limit ?,?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,"等待审核");
            pstmt.setInt(2,start);
            pstmt.setInt(3,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AuthReqBean bean = new AuthReqBean();
                bean.setRequestId(rs.getInt("id"));
                bean.setRoleId(rs.getInt("role_id"));
                bean.setUserId(rs.getInt("user_id"));
                bean.setRoleNameEn(rs.getString("roleName_en"));
                bean.setRoleNameZh(rs.getString("roleName_zh"));
                bean.setUserName(rs.getString("name"));
                bean.setStatus(rs.getString("status"));
                result.add(bean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<AuthReqBean> queryAuthReqBean2(AuthReqBean queryBean,int start,int end){
        List<AuthReqBean> result = new ArrayList<AuthReqBean>();
        String sql = "SELECT t1.status,t1.id,t1.user_id,t1.role_id,t1.roleName_en,t1.roleName_zh," +
                "t2.name from tb_user_role as t1," +
                "manager_info as t2 where t1.user_id = t2.id and t1.status!=? limit ?,?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,"等待审核");
            pstmt.setInt(2,start);
            pstmt.setInt(3,end);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                AuthReqBean bean = new AuthReqBean();
                bean.setRequestId(rs.getInt("id"));
                bean.setRoleId(rs.getInt("role_id"));
                bean.setUserId(rs.getInt("user_id"));
                bean.setRoleNameEn(rs.getString("roleName_en"));
                bean.setRoleNameZh(rs.getString("roleName_zh"));
                bean.setUserName(rs.getString("name"));
                bean.setStatus(rs.getString("status"));
                result.add(bean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void updateAuthReqBean(AuthReqBean queryBean){
        String sql = "update tb_user_role set status = ? where id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setString(1,queryBean.getStatus());
            pstmt.setInt(2,queryBean.getRequestId());
            pstmt.executeUpdate();
            cleanUp(null,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //登录
    public Boolean checkLogin(UserBean userBean){
        String sql = "SELECT * FROM manager_info where id = ? and password = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,userBean.getId());
            pstmt.setString(2,userBean.getPassword());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                userBean.setName(rs.getString("name"));
                sql = "SELECT * FROM tb_user_role where user_id = ? and roleName_en = ?";
                pstmt = getStatement(conn,sql);
                pstmt.setInt(1,userBean.getId());
                pstmt.setString(2,"ADMIN");
                rs = pstmt.executeQuery();
                if(rs.next()){
                    userBean.setAdmin(true);
                }
                return true;
            }
            cleanUp(rs,conn,pstmt);
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //查询用户关联的角色列表
    public Vector<RoleBean> queryUserRoleBean2(Integer userId){
        Vector<RoleBean> roleBeans = new Vector<RoleBean>();
        String sql = "SELECT * FROM tb_user_role where user_id = ? and status = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            pstmt.setInt(1,userId);
            pstmt.setString(2,"审核通过");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                RoleBean roleBean = new RoleBean();
                roleBean.setId(rs.getInt("role_id"));
                roleBean.setNameEn(rs.getString("roleName_en"));
                roleBean.setNameZh(rs.getString("roleName_zh"));
                roleBeans.add(roleBean);
            }
            cleanUp(rs,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleBeans;
    }

    public Boolean hasAuth(Integer userId,String authName){
        List<RoleBean> roleBeans = queryUserRoleBean2(userId);
        if(roleBeans!=null && roleBeans.size()>0){
            List<AuthBean> authBeans = queryRoleAuthBean(roleBeans);
            if(authBeans!=null && authBeans.size()>0){
                for(AuthBean authBean : authBeans){
                    if(authBean.getNameEn().equals(authName)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String list2Str(List<Integer> list){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void setParamValue(PreparedStatement pstmt,int index,Object value) {
        try{
            if(value instanceof Integer){
                pstmt.setInt(index,(Integer)value);
            }else if(value instanceof String){
                pstmt.setString(index,(String)value);
            }else if(value instanceof Date){
                pstmt.setTimestamp(index,new Timestamp(((Date) value).getTime()));
            }else if(value instanceof BigDecimal){
                pstmt.setBigDecimal(index,(BigDecimal)value);
            }
        }catch(Exception e){

        }
    }
    public void hehehe(){
        String sql = "INSERT INTO manager_info() VALUES ()";
        Connection conn = getConnection();
        PreparedStatement pstmt = getStatement(conn,sql);
        try{
            for(int i = 0;i<10000;++i){
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            cleanUp(null,conn,pstmt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();
    }
}

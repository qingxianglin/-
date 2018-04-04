package com.sun.swingset3.utilities;

import com.sun.swingset3.demos.optionpane.OptionPaneDemo;
import com.sun.swingset3.sql.ParkingLotDBUtils;
import com.sun.swingset3.sql.bean.RoleBean;

import javax.management.relation.Role;
import javax.swing.*;
import java.awt.*;

public class AuthUtils {
    private static ParkingLotDBUtils parkingLotDBUtils = new ParkingLotDBUtils();
    public static void CheckAuth(Component parent,JComponent currentDemoPanel, Integer userId, String authName){
        if(LoginInfo.isAdmin || parkingLotDBUtils.hasAuth(userId,authName)){
            currentDemoPanel.setVisible(true);
            return;
        }
        currentDemoPanel.setVisible(false);
        java.util.List<RoleBean> roleBeanList = parkingLotDBUtils.queryAuthRoleBean(authName);
        // Messages
        Object[] message = new Object[3];
        message[0] = "对不起,当前用户没有权限访问此功能!";
        message[1] = "请从如下角色列表中选择一个申请(需要超级管理员审批):";

        JComboBox cb = new JComboBox();
        for(RoleBean roleBean : roleBeanList){
            cb.addItem(roleBean.getId()+"-"+roleBean.getNameZh()+"-"+roleBean.getNameEn());
        }
        message[2] = cb;

        // Options
        String[] options = {
                "提交角色申请",
                "暂不申请"
        };
        int result = JOptionPane.showOptionDialog(
                parent,                        // the parent that the dialog blocks
                message,                                    // the dialog message array
                "当前用户无权限访问该功能", // the title of the dialog window
                JOptionPane.DEFAULT_OPTION,                 // option type
                JOptionPane.INFORMATION_MESSAGE,            // message type
                null,                                       // optional icon, use null to use the default icon
                options,                                    // options string array, will be made into buttons
                options[1]                                  // option that should be made into a default button
        );
        switch (result) {
            case 0: // yes
                String[] array = cb.getSelectedItem().toString().split("-");
                RoleBean roleBean = new RoleBean();
                roleBean.setId(Integer.parseInt(array[0]));
                roleBean.setNameZh(array[1]);
                roleBean.setNameEn(array[2]);
                parkingLotDBUtils.createAuthRequest(roleBean,userId);
                JOptionPane.showMessageDialog(parent, "角色申请已提交,需等待超级管理员审批");
                break;
            default:
                break;
        }
    }
}

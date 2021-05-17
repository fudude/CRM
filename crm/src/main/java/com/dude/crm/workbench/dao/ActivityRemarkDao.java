package com.dude.crm.workbench.dao;

import com.dude.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {


    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    int deleteById(String id);

    int saveRemark(ActivityRemark activityRemark);

    int updateRemark(ActivityRemark ar);
}


//使用该接口类的全限定名称+方法名，拼接就可唯一的定位xml文件中的sql语句的id，
// 通过反射机制，动态的利用jdbc的那一套生成方法，方法的参数是sql语句预编译用的参数
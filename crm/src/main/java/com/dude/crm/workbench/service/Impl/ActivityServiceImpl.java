package com.dude.crm.workbench.service.Impl;


import com.dude.crm.settings.dao.UserDao;
import com.dude.crm.settings.domain.User;
import com.dude.crm.utils.SqlSessionUtil;
import com.dude.crm.vo.PaginationVo;
import com.dude.crm.workbench.dao.ActivityDao;
import com.dude.crm.workbench.dao.ActivityRemarkDao;
import com.dude.crm.workbench.domain.Activity;
import com.dude.crm.workbench.domain.ActivityRemark;
import com.dude.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//写接口和实现类的原因：因为这个动态代理的机制需要接口、类加载器
public class ActivityServiceImpl implements ActivityService {

    //一张表对应一套dao

    //Activity表的dao
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    //ActivityRemark的dao
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public boolean save(Activity a) {
        boolean flag = true;
        int count = activityDao.save(a);
        if (count!=1){
            flag=false;
        }


        return flag;
    }

    @Override
    public PaginationVo<Activity> pageList(Map<String, Object> map) {
        //取得total
        int total = activityDao.getTotalByCondition(map);

        //取得dataList
        List<Activity> datalist = activityDao.getActivityListByCondition(map);

        PaginationVo<Activity> vo = new PaginationVo<Activity>();
        vo.setTotal(total);
        vo.setDataList(datalist);
        //将vo返回
        return vo;
    }

    @Override
    public boolean delete(String[] ids) {

        boolean flag = true;
        //查询出需要删除的备注的数量
        int count1 = activityRemarkDao.getCountByAids(ids);

        //删除备注，返回受到影响的的条数（实际删除的数量）
        int count2 = activityRemarkDao.deleteByAids(ids);
        if (count1!=count2){
            flag = false;
        }
        //删除市场活动
        int count3 = activityDao.delete(ids);
        if (count3!=ids.length){
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {

        //取uList
        List<User> uList = userDao.getUserList();
        //取a
        Activity a = activityDao.getById(id);
        //将uList和a打包到map中
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("uList",uList);
        map.put("a",a);
        //返回map
        return map;
    }

    @Override
    public boolean update(Activity a) {
        boolean flag = true;
        int count = activityDao.update(a);
        if (count!=1){
            flag=false;
        }


        return flag;
    }

    @Override
    public Activity detail(String id) {

        Activity a = activityDao.detail(id);

        return a;
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String activityId) {
        List<ActivityRemark> arList = activityRemarkDao.getRemarkListByAid(activityId);

        return arList;
    }

    @Override
    public boolean deleteRemark(String id) {
        boolean flag = true;
        int count = activityRemarkDao.deleteById(id);

        if (count!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean saveRemark(ActivityRemark activityRemark) {

        boolean flag = true;
        int count = activityRemarkDao.saveRemark(activityRemark);
        if (count!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean updateRemark(ActivityRemark ar) {
        boolean flag = true;
        int count = activityRemarkDao.updateRemark(ar);
        if (count!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Activity> getActivityListByClueId(String clueId) {

        List<Activity> aList = activityDao.getActivityListByClueId(clueId);
        return aList;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotByClueId(Map<String, String> map) {

        List<Activity> aList = activityDao.getActivityListByNameAndNotByClueId(map);

        return aList;
    }

    @Override
    public List<Activity> getActivityListByName(String aname) {

        List<Activity> aList = activityDao.getActivityListByName(aname);

        return aList;
    }
}

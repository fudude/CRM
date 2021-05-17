package com.dude.crm.workbench.service.Impl;

import com.dude.crm.utils.SqlSessionUtil;
import com.dude.crm.workbench.dao.CustomerDao;
import com.dude.crm.workbench.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);


    @Override
    public List<String> getCustomerName(String name) {

        List<String> sList = customerDao.getCustomerName(name);

        return sList;
    }
}

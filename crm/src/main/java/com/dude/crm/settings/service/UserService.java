package com.dude.crm.settings.service;

import com.dude.crm.exception.loginException;
import com.dude.crm.settings.domain.User;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws loginException;

    List<User> getUserList();
}

package com.gz.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

// 用户名密码验证，需要实现抽象类Authenticator的抽象方法PasswordAuthentication
public class MyAuthenricator  extends Authenticator {
    String u = null;
    String p = null;

    public MyAuthenricator(String u, String p) {
        this.u = u;
        this.p = p;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(u, p);
    }

}

package com.djb.highway.framework;

import javax.servlet.http.HttpSession;

import com.djb.highway.carpool.dto.CarpoolUserDTO;



public class SessionManager {

    private static final String SESSION_KEY_CARPOOLUSER_INFO = "carpooluser_info";

    private HttpSession session_ = null;

    public SessionManager(HttpSession session) {
        this.session_ = session;
    }

    public CarpoolUserDTO getCarpoolUserInfo() {

        return (CarpoolUserDTO) this.session_.getAttribute(SESSION_KEY_CARPOOLUSER_INFO);
    }

    public void setCarpoolUserInfo(CarpoolUserDTO carpoolUserInfo) {

        this.session_.setAttribute(SESSION_KEY_CARPOOLUSER_INFO, carpoolUserInfo);
    }

    public boolean getSessionInfo() {

        CarpoolUserDTO scmUserInfo = this.getCarpoolUserInfo();

        if (scmUserInfo == null) {

            return false;
        }
        return true;
    }

    public HttpSession getSession() {
        return this.session_;
    }
}
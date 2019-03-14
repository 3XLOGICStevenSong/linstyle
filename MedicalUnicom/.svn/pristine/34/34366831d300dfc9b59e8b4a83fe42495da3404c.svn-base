/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *
 * System Name        : フレームワーク
 * Description        : ユーザー情報保持
 *
 * 2011/07/28      Wangjiquan
 *
 *++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

package com.djb.ylt.framework;

/**
 * <H3>ログインユーザー情報を保持する.</H3>
 * 
 * <PRE>
 * 
 * 【保持情報】 ログインユーザーID パスワード ログイン時間（ミリ秒） 接続元IPアドレス 担当者CD 担当者名 部門コード 部門名
 * 
 * 
 * </PRE>
 * 
 * @author Wangjiquan
 */
public class UserInfo {

    /** ログインID */
    private String strLoginId = null;

    /** パスワード */
    private String strPassword = null;

    /** ログイン時間 */
    private long lngLoginTime = 0;

    /** 接続元端末IPアドレス */
    private String strIpAddress = null;

    /** 担当者CD */
    private String strEmpCd = null;

    /** 担当者名 */
    private String strEmpName = null;

    /** 部門コード */
    private String strDeptCd = null;

    /** 部門名 */
    private String strDeptName = null;

    /** 上層部門コード */
    private String strUpperDeptCd = null;

    /** 上層部門名 */
    private String strUpperDeptName = null;

    /** 担当者ID */
    private String strEmpId = null;

    /** 兼任フラグ */
    private boolean blnAdditional = false;

    /** 初期表示メニュー */
    private String strInitMenu = null;

    /**
     * <H3>コンストラクタ.</H3>
     * 
     */
    public UserInfo() {
        this.setStrEmpCd("01");
        this.setStrEmpId("wjq");
        this.setStrEmpName("王吉全");
    }

    // 【Getter】---------------------------------------------------------------------------------------------------------
    /**
     * <H3>ログインユーザーのIDを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return ログインID
     */
    public String getStrLoginId() {
        return this.strLoginId;
    }

    /**
     * <H3>ログインパスワードを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return パスワード
     */
    public String getStrPassword() {
        return this.strPassword;
    }

    /**
     * <H3>ログイン時間（ミリ秒）を取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return ログイン時間（ミリ秒）
     */
    public long getLngLoginTime() {
        return this.lngLoginTime;
    }

    /**
     * <H3>担当者CDを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 担当者CD
     */
    public String getStrEmpCd() {
        return this.strEmpCd;
    }

    /**
     * <H3>担当者名を取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 担当者名
     */
    public String getStrEmpName() {
        return this.strEmpName;
    }

    /**
     * <H3>部門コードを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 部門コード
     */
    public String getStrDeptCd() {
        return this.strDeptCd;
    }

    /**
     * <H3>部門名を取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 部門名
     */
    public String getStrDeptName() {
        return this.strDeptName;
    }

    /**
     * <H3>接続元端末IPアドレスを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 接続元端末IPアドレス
     */
    public String getStrIpAddress() {
        return this.strIpAddress;
    }

    /**
     * <H3>上層部門コードを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 上層部門コード
     */
    public String getStrUpperDeptCd() {
        return strUpperDeptCd;
    }

    /**
     * <H3>上層部門名を取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 上層部門名
     */
    public String getStrUpperDeptName() {
        return strUpperDeptName;
    }

    /**
     * <H3>担当者IDを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 担当者ID
     */
    public String getStrEmpId() {
        return this.strEmpId;
    }

    /**
     * <H3>兼任フラグを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 兼任フラグ <BR>
     *         true:兼任あり <BR>
     *         false:兼任なし <BR>
     */
    public boolean isBlnAdditional() {
        return this.blnAdditional;
    }

    /**
     * <H3>
     * 初期表示メニューを取得する.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return 初期表示メニュー区分
     */
    public String getStrInitMenu() {
        return this.strInitMenu;
    }

    // 【Setter】---------------------------------------------------------------------------------------------------------

    /**
     * <H3>ログインユーザーのIDをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strLoginId
     *            ログインID
     */
    public void setStrLoginId(String strLoginId) {
        this.strLoginId = strLoginId;
    }

    /**
     * <H3>ログインパスワードをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strPassword
     *            パスワード
     */
    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    /**
     * <H3>ログイン時間（ミリ秒）をセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param lngLoginTime
     *            ログイン時間（ミリ秒）
     */
    public void setLngLoginTime(long lngLoginTime) {
        this.lngLoginTime = lngLoginTime;
    }

    /**
     * <H3>担当者CDをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strEmpCd
     *            担当者CD
     */
    public void setStrEmpCd(String strEmpCd) {
        this.strEmpCd = strEmpCd;
    }

    /**
     * <H3>担当者名をセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strEmpName
     *            担当者名
     */
    public void setStrEmpName(String strEmpName) {
        this.strEmpName = strEmpName;
    }

    /**
     * <H3>部門コードをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strDeptCd
     *            部門コード
     */
    public void setStrDeptCd(String strDeptCd) {
        this.strDeptCd = strDeptCd;
    }

    /**
     * <H3>部門名をセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strDeptName
     *            部門名
     */
    public void setStrDeptName(String strDeptName) {
        this.strDeptName = strDeptName;
    }

    /**
     * <H3>接続元端末IPアドレスをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strIpAddress
     *            接続元端末IP
     */
    public void setStrIpAddress(String strIpAddress) {
        this.strIpAddress = strIpAddress;
    }

    /**
     * <H3>上層部門コードをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strUpperDeptCd
     *            上層部門コード
     */
    public void setStrUpperDeptCd(String strUpperDeptCd) {
        this.strUpperDeptCd = strUpperDeptCd;
    }

    /**
     * <H3>上層部門名をセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strUpperDeptName
     *            上層部門名
     */
    public void setStrUpperDeptName(String strUpperDeptName) {
        this.strUpperDeptName = strUpperDeptName;
    }

    /**
     * <H3>担当者IDをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strEmpId
     *            担当者ID
     */
    public void setStrEmpId(String strEmpId) {
        this.strEmpId = strEmpId;
    }

    /**
     * <H3>兼任フラグをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param blnAdditonal
     *            兼任フラグ <BR>
     *            true:兼任あり <BR>
     *            false:兼任なし <BR>
     */
    public void setBlnAdditional(boolean blnAdditional) {
        this.blnAdditional = blnAdditional;
    }

    /**
     * <H3>
     * 初期表示メニューをセットする.</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @param strInitMenu
     *            初期表示メニュー区分
     */
    public void setStrInitMenu(String strInitMenu) {
        this.strInitMenu = strInitMenu;
    }

    /**
     * <H3>ユーザー情報を出力する。</H3>
     * 
     * <PRE>
     * </PRE>
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer stb = new StringBuffer();

        stb.append("[LoginId:" + this.strLoginId + "]");
        stb.append("[Password:" + this.strPassword + "]");
        stb.append("[EmpCd:" + this.strEmpCd + "]");
        stb.append("[EmpName:" + this.strEmpName + "]");
        stb.append("[DeptCd:" + this.strDeptCd + "]");
        stb.append("[DeptName:" + this.strDeptName + "]");
        stb.append("[UpperDeptCd:" + this.strUpperDeptCd + "]");
        stb.append("[UpperDeptName:" + this.strUpperDeptName + "]");
        stb.append("[EmpId:" + this.strEmpId + "]");
        stb.append("[LoginTime:" + this.lngLoginTime + "]");
        stb.append("[IpAddress:" + this.strIpAddress + "]");
        stb.append("[Additional:" + this.blnAdditional + "]");

        return stb.toString();
    }

}

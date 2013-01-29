package com.slevenc.spf.web.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Slevenc
 * Date: 13-1-27
 * Time: 下午1:45
 * To change this template use File | Settings | File Templates.
 */
public class FlagException extends RuntimeException {

    private String flag = "EXCEPTION";
    private String msg = "";

    public FlagException(String flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public FlagException(String msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag.toUpperCase();
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

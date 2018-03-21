package com.xzh.weixin.web.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuzh
 * <p>
 * 2014年7月2日 下午12:13:59
 */
public enum ReturnCode {

    ACTIVE_EXCEPTION(-1, "异常"),
    ACTIVE_SUCCESS(0, "成功"),
    ACTIVE_FAILURE(1, "失败"),
    ERROR_PARAMS_NOT_NULL(2, "参数不能为空"),
    ERROR_PARAMS(400, "请求参数错误"),
    ERROR_RESOURCES(404, "请求资源不存在"),
    ERROR_SERVER(503, "系统异常"),
    ERROR_NO_LOGIN(1010, "用户未登录"),
    ERROR_LOGIN_TIMEOUT(1011, "登录用户超时，请重新登录"),
    ERROR_NO_INFO(1014, "无法获取信息"),
    ERROR_LOGIN_FAILURE(1015, "登录失败"),
    ERROR_SYSYTEM_BUSY(1016, "系统繁忙,请等待"),
    ERROR_PERMISSION_DENIED(1017, "操作拒绝");

    private int code;
    private String msg;

    private ReturnCode(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public static ReturnCode codeToEnum(int code) {

        ReturnCode[] values = ReturnCode.values();
        for (ReturnCode returnCode : values) {
            if (returnCode.code == code) {
                return returnCode;
            }
        }
        return ACTIVE_EXCEPTION;
    }

    public int code() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String msg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, ?> Map() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", this.code);
        hashMap.put("msg", this.msg);
        return hashMap;
    }

    public Map<String, ?> Map(int code) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", code);
        hashMap.put("msg", this.msg);
        return hashMap;
    }

    public Map<String, ?> Map(Object msg) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", this.code);
        hashMap.put("msg", msg);
        return hashMap;
    }

    public Map<String, ?> Map(int code, Object msg) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("code", code);
        hashMap.put("msg", msg);
        return hashMap;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{\"code\":").append(this.code).append(",");
        sb.append("\"msg\":\"").append(this.msg).append("\"}");
        return sb.toString();
    }
}

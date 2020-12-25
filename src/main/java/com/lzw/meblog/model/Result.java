package com.lzw.meblog.model;

/**
 * 返回给前端的登录信息
 * @author TongxinWang
 */
public class Result {
    //状态码
    private Integer code;
    //详细信息（是否验证成功）
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

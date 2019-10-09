package com.jason.statusEnum;

public enum StatusEnum {

    SUCCESS("code",200,"成功"),
    FAILED("code",400,"失败"),
    ERROR("code",500,"异常"),
    EXIST("code",401,"已存在"),
    MISSING("code",404,"not found"),
    RESULT("result");

    private String codeName;
    private Integer code;
    private String msg;

    StatusEnum(String codeName, Integer code, String msg){
        this.codeName = codeName;
        this.code = code;
        this.msg = msg;
    }

    StatusEnum(String codeName){
        this.codeName = codeName;
    }

    public String getCodeName() {
        return codeName;
    }

    public StatusEnum setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public StatusEnum setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public StatusEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}

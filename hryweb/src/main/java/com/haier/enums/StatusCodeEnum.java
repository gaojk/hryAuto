package com.haier.enums;

/**
 * @Description: 异常枚举类
 * @Author: luqiwei
 * @Date: 2018/5/16 18:24
 */
public enum StatusCodeEnum {
    SUCCESS(0, "正常处理"),
    PARAMETER_ERROR(1, "参数错误"),
    LOGICAL_ERROR(2, "逻辑错误"),
    SAVE_ERROR(3, "保存失败"),
    NO_IMPLEMENT(4, "方法未实现"),
    EXIST_RECORD(5, "记录已经存在"),
    LOGIN_ERROR(6,"用户名或密码错误"),
    HTTP_ERROR(100,"发送http请求时异常"),
    PARSE_JSON_ERROR(101,"字符串转换为JSONObject时异常"),

    NOT_FOUND(1000, "查询记录不存在！"),
    CHECK_ERROR(1001, "校验不满足条件"),
    END_TIME_BEFORE_START_TIME(1003, "开始时间不能大于结束时间!"),
    PHONE_EMPTY(1004, "手机号码为空"),
    TEMPLATE_PARAMS_EMPTY(1005, "模板参数为空"),
    TEMPLATE_NOT_FOUND(1006, "未找到模板,或者模板不在有效期"),
    PHONE_PARAMS_NOT_CONSISTENT(1007, "手机号与参数不一致"),
    PHONE_FORMAT_ERROR(1008, "手机号格式不对"),
    PARAMS_FORMAT_ERROR(1009, "模板参数格式不对"),
    MQ_SEND_ERROR(1010, "MQ消息发送失败"),
    SMS_RESEND_STATUS_ERROR(1011, "短信状态不是'发送失败',不能重新发送"),

    DB_ERROR(9001, "数据库错误！"),
    PARSE_DATE_ERROR(9002, "时间转换出错！"),
    SYSTEM_MAINTAIN(9990, "第三方系统维护中！"),
    UNKNOWN_BIZ_ERROR(9998, "未知的业务异常！"),
    SYSTEM_ERROR(9999, "系统错误"),
    NETWORK_ERROR(9998, "网络异常");
    ;
    private Integer code;
    private String msg;
    StatusCodeEnum(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "code:"+code+",msg:"+msg;
    }

    /**
     *@description:  通过statusCode获取枚举项
     *@params:
     *@return:
     *@author: luqiwei
     *@date: 2018-05-16
     */
    public static StatusCodeEnum getByCode(Integer statusCode){
        if(statusCode==null){
            return UNKNOWN_BIZ_ERROR;
        }
        for(StatusCodeEnum codeEnum:values()){
            if(codeEnum.getCode()==statusCode){
                return codeEnum;
            }
        }
        return UNKNOWN_BIZ_ERROR;
    }
}

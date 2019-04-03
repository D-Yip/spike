package com.ysj.spike.base;

public class CodeMsg {

    private int code;

    private String msg;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500,"服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(501,"参数检验异常:%s");

    //登录模块502XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(50210,"session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(50220,"密码不能为空");
    public static CodeMsg USERNAME_EMPTY = new CodeMsg(50230,"用户名不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(50240,"手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(50250,"用户不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(50260,"密码错误");

    //商品模块503XX

    //订单模块504XX

    //秒杀模块505XX
    public static CodeMsg SPIKE_OVER = new CodeMsg(50510,"商品已经秒杀完毕");
    public static CodeMsg SPIKE_REPEATE = new CodeMsg(50520,"不能重复秒杀");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg,args);
        return new CodeMsg(code,message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

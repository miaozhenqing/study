package com.example.qzm.study.function.netty.demo.demo_echo.apc;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName Pack
 * @Description TODO
 * @Version 1.0
 **/
public class Apc {
    private String functionName;
    private String parameter;

    public Apc(String functionName, String parameter) {
        this.functionName = functionName;
        this.parameter = parameter;
    }

    public String toJson(){
        return JSONObject.toJSONString(this);
    }


    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}

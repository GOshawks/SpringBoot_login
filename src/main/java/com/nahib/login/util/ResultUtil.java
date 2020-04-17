package com.nahib.login.util;


import com.nahib.login.entity.Result;
import com.nahib.login.entity.ResultEnum;

public class ResultUtil {

    /**
     * 请求重新返回
     * @param object
     * @return
     */
    public static Result success(ResultEnum resultEnum,Object object){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success();
    }

    public static Result error(Integer code, String resultMsg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(resultMsg);
        return result;
    }

    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}

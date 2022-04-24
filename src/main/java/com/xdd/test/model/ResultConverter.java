package com.xdd.test.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ResultConverter {
    public static <T> BasicOutputParam<T> doResult(){
        BasicOutputParam<T> output =new BasicOutputParam();
        output.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        output.setMsg("error");
        return output;
    }
    public static <T> BasicOutputParam<T> doResult(boolean success){
        if (!success){
            return doResult();
        }
        BasicOutputParam<T> output =new BasicOutputParam();
        output.setCode(HttpStatus.OK.value());
        output.setMsg("success");
        return output;
    }
    public static <T> BasicOutputParam<T> doResult(String errMsg){
        BasicOutputParam<T> output =new BasicOutputParam();
        output.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        output.setMsg(errMsg);
        return output;
    }
    public static <T> BasicOutputParam<T> doResult(T data){
        BasicOutputParam<T> output =new BasicOutputParam();
        output.setCode(HttpStatus.OK.value());
        output.setMsg("success");
        int count=0;
        if (data instanceof IPage){
            IPage pageData= (IPage) data;
            output.setData((T) pageData.getRecords());
            count= (int) pageData.getTotal();
        }else if(data != null){
            output.setData(data);
            count=getCount(data);
        }
        output.setCount(count);
        return output;
    }
    public static <T> BasicOutputParam<T> doResult(T data,int count){
        BasicOutputParam<T> output =doResult(data);
        output.setCode(HttpStatus.OK.value());
        output.setMsg("success");
        output.setCount(count);
        return output;
    }

    private static int getCount(Object data){
        int count=1;
        if (data instanceof Map){
            count= ((Map) data).size();
        }else if (data instanceof Collection){
            count= ((Collection) data).size();
        }else if (data != null && data.getClass().isArray()){
            count= Array.getLength(data);
        }
        return count;
    }
}

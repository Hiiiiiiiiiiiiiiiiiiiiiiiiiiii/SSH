package com.kaishengit.ssm.util;

import com.kaishengit.ssm.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class RequestQuery {
    //筛选条件
    private String filterType;
    //筛选值
    private Object value;
    //类的属性
    private String parameterName;

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public static List<RequestQuery> builderRequestQuery(HttpServletRequest request){
        List<RequestQuery> queryList = new ArrayList<>();

        //获取所有查询参数的键值
        Enumeration<String> enumeration = request.getParameterNames();
        while(enumeration.hasMoreElements()){
            //获取筛选信息
            String queryKey = enumeration.nextElement();
            //获取筛选信息所对应的值
            String value = request.getParameter(queryKey);
            //判断value是否为空或者null
            if(queryKey.startsWith("q_")&&value!=null&&!"".equals(value)){
                //拆解筛选信息获取其他信息
                String[] messages = queryKey.split("_");
                //q_marketPrice_eq_bd
                if(messages.length!=4){
                    throw new ServiceException("参数异常");
                }

                RequestQuery requestQuery = new RequestQuery();
                requestQuery.setValue(dealValue(messages[3],value));
                requestQuery.setParameterName(messages[1]);
                requestQuery.setFilterType(messages[2]);
                queryList.add(requestQuery);
            }
        }
        return queryList;

    }
    public static Object dealValue(String valueType,String value){
        if("s".equalsIgnoreCase(valueType)) {
            return value;
        } else if("d".equalsIgnoreCase(valueType)) {
            return Double.valueOf(value);
        } else if("f".equalsIgnoreCase(valueType)) {
            return Float.valueOf(value);
        } else if("i".equalsIgnoreCase(valueType)) {
            return Integer.valueOf(value);
        } else if("bd".equalsIgnoreCase(valueType)) {
            return new BigDecimal(value);
        }
        return null;
    }
}

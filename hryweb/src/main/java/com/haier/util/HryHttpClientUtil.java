package com.haier.util;

import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.haier.enums.ContentTypeEnum;
import com.haier.enums.RequestMethodTypeEnum;
import com.haier.enums.RequestParamTypeEnum;
import com.haier.enums.StatusCodeEnum;
import com.haier.exception.HryException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.Map;
import java.util.Objects;

/**
 * @Description: 封装httpClientUtil
 * @Author: luqiwei
 * @Date: 2018/5/19 11:03
 */
@Slf4j
public class HryHttpClientUtil {

    public static <T> String send(String url, Integer requestMethod, T param) throws HttpProcessException {

        HttpConfig httpConfig;
        if (Objects.isNull(param)) {//参数为null,则不带参发送请求
            httpConfig = HttpConfig.custom().url(url).encoding("utf-8");
        } else if (param instanceof JSONObject) {//参数为json类型,发起json请求

            Header header = new BasicHeader("Content-Type", "application/json;charset=utf-8");
            Header[] headers = {header};
            JSONObject jsonObject = (JSONObject) param;

            httpConfig = HttpConfig.custom().url(url).encoding("utf-8").json(JSONObject.toJSONString(jsonObject));
            httpConfig.headers(headers);

        } else if (param instanceof Map) {//参数为map类型
            Map map = (Map) param;
            httpConfig = HttpConfig.custom().url(url).encoding("utf-8").map(map);
        } else {
            throw new HryException(StatusCodeEnum.PARAMETER_ERROR, "http请求无法发送,暂时只支持Json格式的参数");
        }
        if (requestMethod == RequestMethodTypeEnum.GET.getId()) {
            return HttpClientUtil.get(httpConfig);
        } else {
            return HttpClientUtil.post(httpConfig);
        }
    }

    public static String send(String url, Integer requestMethodType, Integer contentType, Integer requestParamType, String param) {
        if (url == null) {
            return "警告:请求url为null";
        }
        if (requestMethodType == null) {
            requestMethodType = 1;//post
        }
        if (contentType == null) {
            contentType = 1;//application/json
        }
        if (requestParamType == null) {
            requestParamType = 1;//json类型(参数类型)
        }

        //设置请求Headers
        Header header;
        if (contentType == ContentTypeEnum.JSON.getId()) {
            header = new BasicHeader("Content-Type", ContentTypeEnum.JSON.getValue());
        } else {
            return "警告:Content-Type暂时只支持:application/json";
        }

        //设置请求参数
        HttpConfig httpConfig = HttpConfig.custom().url(url).encoding("utf-8").headers(new Header[]{header});
        if (requestParamType == RequestParamTypeEnum.JSON.getId()) {
            if (param != null) {
                httpConfig.json(param);
            }
        }


        //发送请求,返回响应内容
        if (requestMethodType == RequestMethodTypeEnum.GET.getId()) {
            try {
                return HttpClientUtil.get(httpConfig);
            } catch (HttpProcessException e) {
                log.error("发送http请求异常了:", e);
                return "错误:发送http请求异常了-" + e.toString();
            }
        } else {
            try {
                return HttpClientUtil.post(httpConfig);
            } catch (HttpProcessException e) {
                log.error("发送http请求异常了:", e);
                return "错误:发送http请求异常了-" + e.toString();
            }
        }
    }

}

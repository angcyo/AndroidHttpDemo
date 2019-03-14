package com.angcyo.httpdemo;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Email:angcyo@126.com
 *
 * @author angcyo
 * @date 2019/03/14
 * Copyright (c) 2019 ShenZhen O&M Cloud Co., Ltd. All rights reserved.
 */
public interface Api {

    String url = "https://www.baidu.com/";


    //<editor-fold desc="get 请求, 参数 一般出现在url后面">

    @GET(url)
    Observable<ResponseBody> getTest();

    /**
     * 等价于: url?k1=v1&k2=v2&k3=v3
     */
    @GET(url)
    Observable<ResponseBody> getTestParam(@QueryMap Map<String, String> map);

    /**
     * 等价于: api/test/p1/p2
     */
    @GET(url + "api/test/{p1}/{p2}")
    Observable<ResponseBody> getTestParam2(@Path("p1") String p1, @Path("p2") String p2);

    /**
     * 等价于: api/test?p1=p1&p2=p2
     */
    @GET(url + "api/test")
    Observable<ResponseBody> getTestParam3(@Query("p1") String p1, @Query("p2") String p2);

    //</editor-fold desc="get 请求">

    //<editor-fold desc="post 请求, 参数 一般放在 body 里面, 当然也可以放在url后面. 但是 如果放在url后面, 那干嘛不用get?">

    @POST(url)
    Observable<ResponseBody> postTest();

    /**
     * 类似于 web 端的表单请求
     */
    @POST(url)
    @FormUrlEncoded
    Observable<ResponseBody> postTestParam(@FieldMap Map<String, String> map);

    @POST(url)
    Observable<ResponseBody> postTestParam2(@Body RequestBody body);

    //</editor-fold desc="post 请求">

}

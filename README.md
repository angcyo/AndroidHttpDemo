# AndroidHttpDemo
retrofit2 + okhttp3 get post 网络请求示例

---

# Get 请求

## 1. 直接通过`url`请求

![](https://raw.githubusercontent.com/angcyo/AndroidHttpDemo/master/png/getTest.png)

对应的接口声明:
```java
  @GET(url)
  Observable<ResponseBody> getTest();
```

## 2 参数在`path`中

![](https://raw.githubusercontent.com/angcyo/AndroidHttpDemo/master/png/getTestParam1.png)

对应的接口声明:
```java
    @GET(url)
    Observable<ResponseBody> getTestParam(@QueryMap Map<String, String> map);
```

## 3 参数在`url`后

![](https://raw.githubusercontent.com/angcyo/AndroidHttpDemo/master/png/getTestParam2.png)

对应的接口声明:
```java
    @GET(url + "api/test/{p1}/{p2}")
    Observable<ResponseBody> getTestParam2(@Path("p1") String p1, @Path("p2") String p2);
```

![](https://raw.githubusercontent.com/angcyo/AndroidHttpDemo/master/png/getTestParam3.png)

对应的接口声明:
```java
    @GET(url + "api/test")
    Observable<ResponseBody> getTestParam3(@Query("p1") String p1, @Query("p2") String p2);
```


# Post 请求

## 1. 无参的`post`请求和`get`请求 是一样的

![](https://raw.githubusercontent.com/angcyo/AndroidHttpDemo/master/png/getTest.png)

对应的接口声明:
```java
    @POST(url)
    Observable<ResponseBody> postTest();
```

## 2 表单形式的`post`请求

![](https://raw.githubusercontent.com/angcyo/AndroidHttpDemo/master/png/postTestParam1.png)

**注意图中的`Content-Type`和`Body`字段**

对应的接口声明:
```java
    @POST(url)
    Observable<ResponseBody> postTest();
```

## 3 Json形式的`post`请求

![](https://raw.githubusercontent.com/angcyo/AndroidHttpDemo/master/png/postTestParam2.png)

**注意图中的`Content-Type`和`Body`字段**

对应的接口声明:
```java
    @POST(url)
    Observable<ResponseBody> postTestParam2(@Body RequestBody body);
```

---
**群内有`各(pian)种(ni)各(jin)样(qun)`的大佬,等你来撩.**

# 联系作者
[点此快速加群](https://shang.qq.com/wpa/qunwpa?idkey=cbcf9a42faf2fe730b51004d33ac70863617e6999fce7daf43231f3cf2997460)

> 请使用QQ扫码加群, 小伙伴们都在等着你哦!

![](https://raw.githubusercontent.com/angcyo/res/master/image/qq/qq_group_code.png)

> 关注我的公众号, 每天都能一起玩耍哦!

![](https://raw.githubusercontent.com/angcyo/res/master/image/weixin/%E8%AE%A2%E9%98%85%E5%8F%B7_%E4%BA%8C%E7%BB%B4%E7%A0%81/qrcode_for_gh_59fa6d9a51d8_258_8cm.jpg)

 
  

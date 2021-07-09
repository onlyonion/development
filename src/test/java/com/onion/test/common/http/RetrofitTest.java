package com.onion.test.common.http;

import com.alibaba.fastjson.JSON;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.onion.test.common.http.model.DingtalkRequest;
import com.onion.test.common.http.model.DingtalkRequest.Text;
import com.onion.test.common.http.model.DingtalkResult;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

import java.io.IOException;

/**
 * @author lijicong
 * @since 2021-07-09
 */
public class RetrofitTest {

    interface YoudaoApi {
        @POST("translate_o")
        Object translate_o();
    }

    @Test
    public void testYoudaoApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .build();


    }

    public interface DingtalkApi {
        // https://oapi.dingtalk.com/robot/send?access_token=265de9dada597b16cb11950ad8636b7ec43919413bc991561072ce435314fdb9
        @POST("/robot/send")
        DingtalkResult<Void> robotSend(@Query("access_token") String accessToken, @Body DingtalkRequest request);

        @POST("/robot/send")
        Call<ResponseBody> robotSend2(@Query("access_token") String accessToken, @Body DingtalkRequest request);

    }

    @Test
    public void testDingtalkApi() throws IOException {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava平台
                .build();
        DingtalkApi dingtalkApi = retrofit2.create(DingtalkApi.class);

        DingtalkRequest dingtalkRequest = new DingtalkRequest();
        dingtalkRequest.setMsgtype("text");
        Text text = new Text();
        text.setContent("hello");
        dingtalkRequest.setText(text);
        Call<ResponseBody> result = dingtalkApi.robotSend2("265de9dada597b16cb11950ad8636b7ec43919413bc991561072ce435314fdb9", dingtalkRequest);
        result.execute();
        System.out.println(JSON.toJSONString(result));
    }

    public interface GetRequest_Interface {
        /**
         *表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
         * <code>Field("username")</code> 表示将后面的 <code>String name</code> 中name的取值作为 username 的值
         */
        @POST("/form")
        @FormUrlEncoded
        Call<ResponseBody> testFormUrlEncoded1(@Field("username") String name, @Field("age") int age);

        /**
         * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
         * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
         */
        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload1(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);

    }

/*    @Test
    public void test1() {
        // 具体使用
        GetRequest_Interface service = retrofit.create(GetRequest_Interface.class);
        // @FormUrlEncoded
        Call<ResponseBody> call1 = service.testFormUrlEncoded1("Carson", 24);

        //  @Multipart
        RequestBody name = RequestBody.create(textType, "Carson");
        RequestBody age = RequestBody.create(textType, "24");

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
        Call<ResponseBody> call3 = service.testFileUpload1(name, age, filePart);
    }*/
}

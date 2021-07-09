package com.onion.test.common.http;

import com.alibaba.fastjson.JSON;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import java.util.concurrent.TimeUnit;

/**
 * @author lijicong
 * @since 2021-07-09
 */
@Slf4j
public class RetrofitRxJavaTest {

    @Test
    public void test() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // b. 创建 网络请求接口 的实例
        DemoApi demoApi = retrofit.create(DemoApi.class);

        Observable<Object> observable = demoApi.sugrec();
        log.info("observable {}", JSON.toJSONString(observable));

        observable.subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object result) {
                        log.info("{}", result);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    interface DemoApi {
        @GET("sugrec")
        Observable<Object> sugrec();
    }

    @Test
    public void test2() {
        Observable.interval(2, 1, TimeUnit.SECONDS)
                // 参数说明：
                // 参数1 = 第1次延迟时间；
                // 参数2 = 间隔时间数字；
                // 参数3 = 时间单位；
                // 该例子发送的事件特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                /*
                 * 步骤2：每次发送数字前发送1次网络请求（doOnNext（）在执行Next事件前调用）
                 * 即每隔1秒产生1个数字前，就发送1次网络请求，从而实现轮询需求
                 **/
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long integer) throws Exception {
                        log.info("第 " + integer + " 次轮询");
                        /*
                         * 步骤3：通过Retrofit发送网络请求
                         **/
                        // a. 创建Retrofit对象
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://fy.iciba.com/") // 设置 网络请求 Url
                                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                                .build();

                        // b. 创建 网络请求接口 的实例
                        DemoApi demoApi = retrofit.create(DemoApi.class);

                        // c. 采用Observable<...>形式 对 网络请求 进行封装
                        Observable<Object> observable = demoApi.sugrec();
                        // d. 通过线程切换发送网络请求
                        observable.subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                                .subscribe(new Observer<Object>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                    }
                                    @Override
                                    public void onNext(Object result) {
                                        // e.接收服务器返回的数据
                                        log.info("{}", result);
                                    }
                                    @Override
                                    public void onError(Throwable e) {
                                        log.info( "请求失败");
                                    }
                                    @Override
                                    public void onComplete() {

                                    }
                                });

                    }
                }).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Long value) {
                    }
                    @Override
                    public void onError(Throwable e) {
                        log.info("对Error事件作出响应");
                    }
                    @Override
                    public void onComplete() {
                        log.info("对Complete事件作出响应");
                    }
                });
    }
}

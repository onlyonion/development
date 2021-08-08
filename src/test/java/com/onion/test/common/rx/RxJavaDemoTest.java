package com.onion.test.common.rx;

import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import io.reactivex.Observable;

@Slf4j
public class RxJavaDemoTest {

    @Test
    public void test() {
        // 1. 创建被观察者 Observable 对象
        Observable<Integer> observable = Observable.create(emitter -> {
            // create() 是 RxJava 最基本的创造事件序列的方法
            // 此处传入了一个 OnSubscribe 对象参数
            // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的复写方法从而响应事件
            // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });

        // 扩展：RxJava 提供了其他方法用于 创建被观察者对象Observable -->
        // 方法1：just(T...)：直接将传入的参数依次发送出来
        Observable observable2 = Observable.just("A", "B", "C");
        // 将会依次调用：
        // onNext("A");
        // onNext("B");
        // onNext("C");
        // onCompleted();

        // 方法2：from(T[]) / from(Iterable<? extends T>) : 将传入的数组 / Iterable 拆分成具体对象后，依次发送出来
        String[] words = {"A", "B", "C"};
        Observable observable3 = Observable.fromArray(words);
        // 将会依次调用：
        // onNext("A");
        // onNext("B");
        // onNext("C");
        // onCompleted();

    }

    // 注：整体方法调用顺序：
    // 观察者.onSubscribe()
    // 被观察者.subscribe()
    // 观察者.onNext()
    // 观察者.onComplete()
}

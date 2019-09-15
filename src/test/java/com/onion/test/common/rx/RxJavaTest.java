package com.onion.test.common.rx;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RxJavaTest {

    @Test
    public void helloworld() {
        Observable.just("hello world").subscribe(System.out::print);
    }

    @Test
    public void test1() {
        Observable<Integer> observable = Observable.create(e -> {
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
            e.onComplete();
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                log.info("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                log.info("onNext {}", integer);
            }

            @Override
            public void onError(Throwable e) {
                log.info("onError e={}", e);
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        };

        observable.subscribe(observer);
    }

}

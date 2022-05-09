package com.onion.test.java.lang;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.elasticsearch.common.recycler.Recycler.V;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author lijicong
 * @since 2022-03-03
 */
public class ComparableTest {

    @Test
    public void test() {
        // -Djava.util.Arrays.useLegacyMergeSort=false
        List<AB> list = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            AB b = new AB((double) i);
            list.add(b);
        }
        Collections.sort(list);
        // Collections.reverse(list);
        System.out.println(JSON.toJSONString(list));

        List<B> list2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            B b = new B((double) i);
            list2.add(b);
        }
        Collections.sort(list2);
        System.out.println(JSON.toJSONString(list2));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class AB implements Comparable<AB> {
        Double score;
        @Override
        public int compareTo(@NotNull AB o) {
            return score - o.getScore() >= 0 ? -1 : 1;
            // return score.compareTo(o.getScore());
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    static class B implements Comparable<B> {
        Double score;
        @Override
        public int compareTo(@NotNull B o) {
            // return score - o.getScore() >= 0 ? -1 : 1;
            return score.compareTo(o.getScore());
        }
    }
}

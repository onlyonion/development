package com.onion.test.distributed.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SentinelTest {

    private static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        // 配置规则.
        initFlowRules();

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            //SphU.entry进入资源，成功则执行内部逻辑
            try (Entry entry = SphU.entry("HelloWorld")) {
                Thread.sleep(0);
                // 被保护的逻辑
                System.out.println("hello world, num:" + num.incrementAndGet());
            } catch (BlockException ex) {
                // 处理被流控的逻辑, 已经超出限制则抛出异常
                System.out.println("blocked! " + ex.getMessage() + " num:" + num.get());
            }
        }
    }

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        //限流规则
        FlowRule rule = new FlowRule();
        //资源名字
        rule.setResource("HelloWorld");
        //限制类型，有qps和线程数，这里是线程数
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        //限制1秒最多只能进20个请求
        rule.setCount(20);
        rules.add(rule);
        //加载规则，在内存中进行维护的
        FlowRuleManager.loadRules(rules);
    }

}

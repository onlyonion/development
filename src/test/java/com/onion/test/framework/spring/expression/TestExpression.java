package com.onion.test.framework.spring.expression;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class TestExpression {

    @Test
    public void test1()  {
        String greetingExp = "Hello, #{ #user }";

        // 表达式解析器
        ExpressionParser parser = new SpelExpressionParser();

        // 求值上下文
        EvaluationContext context = new StandardEvaluationContext();

        context.setVariable("user", "Gangyou");

        Expression expression = parser.parseExpression(greetingExp, new TemplateParserContext());

        System.out.println(expression.getValue(context));
        System.out.println(expression.getValue(context, String.class));
    }

    @Test
    public void test2() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello '+' World!'");
        String result = exp.getValue().toString();
        System.out.println(result);
    }

    @Test
    public void test3() {
        ExpressionParser parser = new SpelExpressionParser();
        User user = new User(9527, "周星驰");
        EvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("user", user);

        System.out.println(parser.parseExpression("#user.getId() + 1900").getValue(ctx));
        System.out.println(parser.parseExpression("#user.id").getValue(ctx));
        System.out.println(parser.parseExpression("'Hello'.toUpperCase()").getValue());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class User {
        private int id;
        private String name;
    }
}

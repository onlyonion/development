package com.onion.test.common.drools;

import java.util.Collection;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class FirstTest {

    public static void main(String[] args) {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("com/onion/test/common/drools/first.drl", FirstTest.class), ResourceType.DRL);

        Collection<KnowledgePackage> knowledgePackages = builder.getKnowledgePackages();
        KnowledgeBase knowledgeBase = builder.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgePackages);
        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();

        Person person = new Person();
        person.setName("hello");
        ksession.insert(person);
        ksession.fireAllRules();
        ksession.dispose();
    }

}

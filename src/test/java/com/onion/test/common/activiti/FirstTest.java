package com.onion.test.common.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class FirstTest {

    public static void main(String[] args) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();
        repositoryService.createDeployment().addClasspathResource("").deploy();
        runtimeService.startProcessInstanceByKey("process");

        Task task = taskService.createTaskQuery().singleResult();
        System.out.println(task.getName());

        taskService.complete(task.getId());
        task = taskService.createTaskQuery().singleResult();
        System.out.println(task.getName());

        taskService.complete(task.getId());
        task = taskService.createTaskQuery().singleResult();
        System.out.println(task.getName());

    }

}

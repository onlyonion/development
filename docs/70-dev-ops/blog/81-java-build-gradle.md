```groovy
task hellowrold {
    doLast {
        println 'hello world
    }
}

task helloworld << {
    println 'hello world
}

```

### resolutionStrategy
```groovy
    resolutionStrategy {
        cacheChangingModulesFor 0, 'seconds'
        cacheDynamicVersionsFor 0, 'seconds'
    }
```
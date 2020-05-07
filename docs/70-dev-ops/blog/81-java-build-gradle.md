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
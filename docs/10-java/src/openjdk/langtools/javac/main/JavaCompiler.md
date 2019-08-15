com.sun.tools.javac.main.JavaCompiler

## methods

### compile
```java
    public void compile(List<JavaFileObject> sourceFileObjects,
                        List<String> classnames,
                        Iterable<? extends Processor> processors)
    {
        if (processors != null && processors.iterator().hasNext())
            explicitAnnotationProcessingRequested = true;
        // as a JavaCompiler can only be used once, throw an exception if
        // it has been used before.
        if (hasBeenUsed)
            throw new AssertionError("attempt to reuse JavaCompiler");
        hasBeenUsed = true;

        // forcibly set the equivalent of -Xlint:-options, so that no further
        // warnings about command line options are generated from this point on
        options.put(XLINT_CUSTOM.text + "-" + LintCategory.OPTIONS.option, "true");
        options.remove(XLINT_CUSTOM.text + LintCategory.OPTIONS.option);

        start_msec = now();

        try {
            initProcessAnnotations(processors); // 准备过程：初始化插入式注解处理器

            // These method calls must be chained to avoid memory leaks
            delegateCompiler =
                processAnnotations( // 执行注解处理器 
                    enterTrees(stopIfError(CompileState.PARSE, parseFiles(sourceFileObjects))), // parseFiles 词法分析、语法分析 
                    classnames);

            delegateCompiler.compile2(); // 语义分析及字节码生成
            delegateCompiler.close();
            elapsed_msec = delegateCompiler.elapsed_msec;
        } catch (Abort ex) {
            if (devVerbose)
                ex.printStackTrace(System.err);
        } finally {
            if (procEnvImpl != null)
                procEnvImpl.close();
        }
    }
```


### compile2
```java
    /**
     * The phases following annotation processing: attribution,
     * desugar, and finally code generation.
     */
    private void compile2() {
        try {
            switch (compilePolicy) {
            case ATTR_ONLY:
                attribute(todo);
                break;

            case CHECK_ONLY:
                flow(attribute(todo));
                break;

            case SIMPLE:
                generate(desugar(flow(attribute(todo))));
                break;

            case BY_FILE: {
                    Queue<Queue<Env<AttrContext>>> q = todo.groupByFile();
                    while (!q.isEmpty() && !shouldStop(CompileState.ATTR)) {
                        generate(desugar(flow(attribute(q.remove()))));
                    }
                }
                break;

            case BY_TODO:
                while (!todo.isEmpty())
                    generate(desugar(flow(attribute(todo.remove())))); // 标注处理、数据流处理、解语法糖、生成字节码
                break;

            default:
                Assert.error("unknown compile policy");
            }
        } catch (Abort ex) {
            if (devVerbose)
                ex.printStackTrace(System.err);
        }

        if (verbose) {
            elapsed_msec = elapsed(start_msec);
            log.printVerbose("total", Long.toString(elapsed_msec));
        }

        reportDeferredDiagnostics();

        if (!log.hasDiagnosticListener()) {
            printCount("error", errorCount());
            printCount("warn", warningCount());
        }
    }
```
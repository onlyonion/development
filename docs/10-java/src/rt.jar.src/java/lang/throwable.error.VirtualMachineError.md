java.lang.VirtualMachineError

## hierarchy
```
Throwable (java.lang)
    Error (java.lang)
        VirtualMachineError (java.lang)
            InternalError (java.lang)
                ZipError (java.util.zip)
            OutOfMemoryError (java.lang)
            StackOverflowError (java.lang)
            UnknownError (java.lang)
```


## OutOfMemoryError
OOM之前一般会伴随FullGC
- Java heap space
- method area
  - PermGen
  - Metaspace
- direct buffer memory
- GC overhead limit exceeded
- Socket缓冲区 IOException: too many open files
- OutOfDirectMemoryError

## StackOverflowError
unable to create new native thread


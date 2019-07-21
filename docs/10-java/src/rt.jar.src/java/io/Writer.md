java.io.Writer

## hierarchy
```
Writer (java.io)
    BufferedWriter (java.io)
    CharArrayWriter (java.io)
    FilterWriter (java.io)
    OutputStreamWriter (java.io)
    PipedWriter (java.io)
    PrintWriter (java.io)
    StringWriter (java.io)
Writer (java.io)
    Appendable (java.lang)
    Closeable (java.io)
    Flushable (java.io)
```

## define
```plantuml
@startuml

abstract class Writer

@enduml
```

## fields
```java
    private char[] writeBuffer;
    private static final int WRITE_BUFFER_SIZE = 1024;
    protected Object lock;
```

## methods

### write
```java
    public void write(int c) throws IOException {
        synchronized (lock) {
            if (writeBuffer == null){
                writeBuffer = new char[WRITE_BUFFER_SIZE];
            }
            writeBuffer[0] = (char) c;
            write(writeBuffer, 0, 1);
        }
    }
    
    public void write(char cbuf[]) throws IOException {
        write(cbuf, 0, cbuf.length);
    }
    
    abstract public void write(char cbuf[], int off, int len) throws IOException;

    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }
    
    public void write(String str, int off, int len) throws IOException {
        synchronized (lock) {
            char cbuf[];
            if (len <= WRITE_BUFFER_SIZE) {
                if (writeBuffer == null) {
                    writeBuffer = new char[WRITE_BUFFER_SIZE];
                }
                cbuf = writeBuffer;
            } else {    // Don't permanently allocate very large buffers.
                cbuf = new char[len];
            }
            str.getChars(off, (off + len), cbuf, 0);
            write(cbuf, 0, len);
        }
    }
```
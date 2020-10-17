com.mysql.jdbc.StandardSocketFactory


## Methods

### connect
- socket 
  - new -> bind -> connect
  - new -> bind -> listen -> accept

```java
    public Socket connect(String hostname, int portNumber, Properties props) throws SocketException, IOException {
            // ... 
            for (int i = 0; i < possibleAddresses.length; i++) {
                try {
                    this.rawSocket = createSocket(props);

                    configureSocket(this.rawSocket, props);

                    InetSocketAddress sockAddr = new InetSocketAddress(possibleAddresses[i], this.port);
                    // bind to the local port if not using the ephemeral port
                    if (localSockAddr != null) {
                        this.rawSocket.bind(localSockAddr);
                    }

                    this.rawSocket.connect(sockAddr, getRealTimeout(connectTimeout));

                    break;
                } catch (SocketException ex) {
                    lastException = ex;
                    resetLoginTimeCountdown();
                    this.rawSocket = null;
                }
            }
            // ... 
    }
```
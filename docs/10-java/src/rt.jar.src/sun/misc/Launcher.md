sun.misc.Launcher

## define
```plantuml
@startuml

class Launcher {
    - static URLStreamHandlerFactory factory = new Launcher.Factory()
    - static Launcher launcher = new Launcher()
    - static String bootClassPath = System.getProperty("sun.boot.class.path")
    - ClassLoader loader
    - static URLStreamHandler fileHandler
}

Launcher +-- BootClassPathHolder
Launcher +-- ExtClassLoader
Launcher +-- AppClassLoader

abstract class ClassLoader
class SecureClassLoader 
class URLClassLoader 

class ExtClassLoader
class AppClassLoader

ClassLoader ^-- SecureClassLoader
SecureClassLoader ^-- URLClassLoader
URLClassLoader ^-- ExtClassLoader
URLClassLoader ^-- AppClassLoader

@enduml
```
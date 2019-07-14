javax.script
## package
```
AbstractScriptEngine
Bindings
Compilable
CompiledScript
Invocable
ScriptContext
ScriptEngine
ScriptEngineFactory
ScriptEngineManager
ScriptException
SimpleBindings
SimpleScriptContext
```

## overview
```plantuml
@startuml

interface ScriptEngine
abstract class AbstractScriptEngine

AbstractScriptEngine *- ScriptContext
class NashornScriptEngine

ScriptEngine ^.. AbstractScriptEngine
AbstractScriptEngine ^-- NashornScriptEngine


interface ScriptEngineFactory 
ScriptEngine <.. ScriptEngineFactory

class ScriptEngineManager
ScriptEngineManager "1" *-- "*" ScriptEngineFactory

interface ScriptContext
ScriptContext ^.. SimpleScriptContext
@enduml
```

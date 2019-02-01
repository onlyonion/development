```
@startuml

interface Authenticator
interface RegistrationListener
abstract class ValveBase

abstract class AuthenticatorBase {
    + void invoke(Request request, Response response)
}

ValveBase <|-- AuthenticatorBase

Authenticator <|.. AuthenticatorBase
RegistrationListener <|.. AuthenticatorBase
@enduml
```
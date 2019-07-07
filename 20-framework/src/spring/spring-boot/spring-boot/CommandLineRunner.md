org.springframework.boot.CommandLineRunner
## hierarchy
```
CommandLineRunner (org.springframework.boot)
    JobLauncherCommandLineRunner (org.springframework.boot.autoconfigure.batch)
```
## define
```java
public interface CommandLineRunner {
	void run(String... args) throws Exception;
}
```
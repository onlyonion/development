org.springframework.data.hadoop.hbase.HbaseTemplate

## Hierarchy
```
HbaseAccessor (org.springframework.data.hadoop.hbase)
    HbaseTemplate (org.springframework.data.hadoop.hbase)
        WrappedHbaseTemplate (cn.com.duibaboot.ext.autoconfigure.data.hbase)
```
## Define
```plantuml
@startuml

abstract class HbaseAccessor
interface HbaseOperations
class HbaseTemplate

HbaseAccessor ^-- HbaseTemplate
HbaseOperations ^.. HbaseTemplate

interface HTableInterfaceFactory
HbaseAccessor *-- HTableInterfaceFactory
HbaseAccessor *-- Configuration

@enduml
```
```java
public class HbaseTemplate extends HbaseAccessor implements HbaseOperations {
	private boolean autoFlush = true;
	public HbaseTemplate(Configuration configuration) {
		setConfiguration(configuration);
		afterPropertiesSet();
	}
}
```

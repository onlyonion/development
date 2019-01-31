## Summary of BlockingQueue methods
| method  | Throws exception | Special value | Blocks         | Times out            |
| :------ | :--------------- | :------------ | :------------- | :------------------- |
| Insert  | add(e)           | offer(e)      | put(e)         | offer(e, time, unit) |
| Remove  | remove()         | poll()        | take()         | poll(time, unit)     |
| Examine | element()        | peek()        | not applicable | not applicable       |
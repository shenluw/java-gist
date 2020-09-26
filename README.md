# java-gist

java 代码片段

## 模块说明

### enums

枚举辅助函数

经常有业务类似

```java
enum Type{
    A(1),B(2),C(3);   
    
    int code;
    Type(int code) {
        this.code = code;        
    }
    
    Type of(int code){
        ....
    }    
}
```
辅助函数主要针对上述情况自动生成相关函数。

### subsectionexecutor

将一个很大的集合任务拆分成N个小任务执行

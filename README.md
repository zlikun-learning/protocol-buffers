# Protocol Buffers

- <https://developers.google.cn/protocol-buffers/>
- <https://developers.google.cn/protocol-buffers/docs/proto3>
- <https://developers.google.cn/protocol-buffers/docs/encoding>
- <https://developers.google.cn/protocol-buffers/docs/javatutorial>
- <https://developers.google.cn/protocol-buffers/docs/reference/java-generated>
- <https://blog.csdn.net/u011518120/article/details/54604615>

```xml
<dependency>
    <groupId>com.google.protobuf</groupId>
    <artifactId>protobuf-java</artifactId>
    <version>3.5.1</version>
</dependency>
```

#### 标量值类型
| proto type | java type | default value | comments |
| :---: | :---: | :---: | --- |
| double | double | 0 |
| float | float | 0 |
| int32 | int | 0 |
| int64 | long | 0 |
| uint32 | int | 0 | Uses variable-length encoding |
| uint64 | long | 0 | Uses variable-length encoding |
| sint32 | int | 0 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int32s |
| sint64 | long | 0 | Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int64s |
| fixed32 | int | 0 | Always four bytes. More efficient than uint32 if values are often greater than 2^28 |
| fixed64 | long | 0 | Always eight bytes. More efficient than uint64 if values are often greater than 2^56 |
| sfixed32 | int | 0 | Always four bytes |
| sfixed64 | long | 0 | Always eight bytes |
| bool | boolean | false |
| string | String | empty string | A string must always contain UTF-8 encoded or 7-bit ASCII text |
| bytes | ByteString | empty bytes | May contain any arbitrary sequence of bytes |
| enums |  | first defined enum value |

#### 编译生成Java类
```
$ protoc --proto_path=IMPORT_PATH --java_out=DST_DIR path/to/file.proto
$ protoc --proto_path=./ --java_out ./ DataMap.proto
```

#### 与Maven集成
- <https://www.xolstice.org/protobuf-maven-plugin/index.html>

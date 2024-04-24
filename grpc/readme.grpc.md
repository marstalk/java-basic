1. Install protobuf compiler on Mac:
```bash
brew install protobuf
```
2. define xx.proto file
```proto
syntax="";

service ...

message ...
```

2. execute command
```bash
protoc --java_out=/path/to/place/outout /path/to/greeting.proto
```

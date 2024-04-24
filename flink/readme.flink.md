# 本地执行的Demo
使用pom.local.xml，主要区别是将flink相关依赖的scope设置为comile&runtime

## Demo1
流式处理Socket输入，分组统计时间窗口内的单词。SocketWindowWordCount.java

How to run:
1. Use command `nc -l 9000` to create a tcp server listening port 9000 on local computer terminal, it will block for input.
2. Run or debug SocketWindowWordCount.java and check the log in console with key line `Connecting to server socket localhost:9000`
3. Type whatever you want in terminal
4. Check the console.

## Demo2
批处理，分组统计txt文件中的单词。BatchWordCountJava.java

How to run:
1. 把txt文件放在`./a_wordcount2/input`目录中
2. Run BatchWordCountJava
3. 在`./a_wordcount2/output/output.txt`中检查输出


# flink集群中运行
以demo1为例子
1. 下载flink对应版本，启动flink单节点集群
```./bin/start-cluster.sh```
2. 使用pom.cluster.xml替换pom.xml（主要是将flink相关依赖的scope改为provided，并增加编译&打包的插件，将其他依赖打进jar包，并制定mainClass），并修改mainClass为
```flinkdemo.a_wordcount.SocketWindowWordCount```
3. 打包
```mvn clean pacage```
4. 将以'jar-with-dependencies'为后缀的jar包提交给集群
```./bin/flink run flink-1.0-SNAPSHOT-jar-with-dependencies.jar```

5. 查看任务
```./bin/flink list```
6. 取消任务
```bash
./bin/flink canel @@job-id@@
```

# Data source

# transformer
- union: union类型要求一致，可以关联多个。flinkdemo.c_tramsformer.UnionStream，
- connect: 类型可以不一致，通过coMap等转为一致的类型，只能connect两个。
- Split：切流之后，通过select来选择多流。

# partition
https://www.bilibili.com/video/BV17t4y1h7AW?p=22&spm_id_from=pageDriver&vd_source=1309b1a397ebdf226238dc48a723de62





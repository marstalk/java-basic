package flinkdemo.a_wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * requirement:: <br/>
 * 1. read socket input with delimiter of RETURN. <br/>
 * 2. for every string message: <br/>
 * a. flat by SPACE <br/>
 * b. map to wordCount <br/>
 * c. groupby wordCount.word <br/>
 * d. set slide window <br/>
 * e. sum up wordCount.count inner the slide window. <br/>
 */
public class SocketWindowWordCount {

    public static void main(String[] args) throws Exception {
        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        int port;
        String host = null;
        try {
            port = parameterTool.getInt("port");
            host = parameterTool.get("host");
        } catch (Exception e) {
            System.out.println("no host and port provide, use default localhost:9000");
            host = "localhost";
            port = 9000;
        }

        // get env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        String delimiter = "\n";
        DataStreamSource<String> text = env.socketTextStream(host, port, delimiter);

        SingleOutputStreamOperator<WordCount> windowCounts = text.flatMap((FlatMapFunction<String, WordCount>) (line, collector) -> {
                    String[] splits = line.split("\\s");
                    for (String word : splits) {
                        collector.collect(new WordCount(word, 1L));
                    }
                })
                .returns(WordCount.class)
                .keyBy("word")
                .timeWindow(Time.seconds(2), Time.seconds(1))// 指定时间窗口为2秒，时间间隔为1秒。
                .sum("count");

        // 打印到控制台
        windowCounts.print().setParallelism(1);

        // 执行任务，类似Java stream中的
        env.execute("Socket window count");
    }


}

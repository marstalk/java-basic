package flinkdemo.b_customsource;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

public class CustomDataSourceStreaming {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 对于不支持并行度的datasource，如果设置的并行度!=1，会异常。
        DataStreamSource<Long> dataStreamSource = env.addSource(new NoParalleSource()).setParallelism(1);

        SingleOutputStreamOperator<Long> sum = dataStreamSource.map((MapFunction<Long, Long>) value -> {
            System.out.println("received: " + value);
            return value;
        }).timeWindowAll(Time.seconds(2)).sum(0);

        sum.print().setParallelism(1);

        env.execute(CustomDataSourceStreaming.class.getSimpleName());
    }
}

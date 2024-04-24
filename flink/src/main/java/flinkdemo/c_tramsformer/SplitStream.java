package flinkdemo.c_tramsformer;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SplitStream {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<Integer> integerDataStreamSource = env.addSource(new SourceFunction<Integer>() {
            @Override
            public void run(SourceContext<Integer> sourceContext) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    sourceContext.collect(i);
                    TimeUnit.SECONDS.sleep(1);
                }
            }

            @Override
            public void cancel() {

            }
        });

        DataStream<Integer> select = integerDataStreamSource.split(new OutputSelector<Integer>() {
            @Override
            public Iterable<String> select(Integer integer) {
                ArrayList<String> strings = new ArrayList<>();
                if (integer % 2 == 0) {
                    // 相当于给符合这个条件的分支打了一个标签，后面在select的时候通过标签来选择一个或者多个不同的流。
                    strings.add("even");
                } else {
                    strings.add("odd");
                }
                return strings;
            }
        }).select("even");

        select.print().setParallelism(1);

        env.execute();
    }
}

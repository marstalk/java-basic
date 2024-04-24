package flinkdemo.c_tramsformer;

import flinkdemo.b_customsource.NoParalleSource;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.concurrent.TimeUnit;

public class UnionStream {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 0, 2, 4, 6 ...
        DataStreamSource<Long> stream1 = env.addSource(new SourceFunction<Long>() {
            @Override
            public void run(SourceContext<Long> sourceContext) throws Exception {
                long i = 0;
                while(true){
                    sourceContext.collect(i);
                    i += 2;
                    TimeUnit.SECONDS.sleep(1);
                }
            }

            @Override
            public void cancel() {

            }
        });
        // 1, 3, 5, 7 ...
        DataStreamSource<Long> stream2 = env.addSource(new SourceFunction<Long>() {
            @Override
            public void run(SourceContext<Long> sourceContext) throws Exception {
                long i = 1;
                while(true){
                    sourceContext.collect(i);
                    i += 2;
                    TimeUnit.SECONDS.sleep(1);
                }
            }

            @Override
            public void cancel() {

            }
        });

        DataStreamSource<Long> stream3 = env.addSource(new SourceFunction<Long>() {
            @Override
            public void run(SourceContext<Long> sourceContext) throws Exception {
                while (true) {
                    sourceContext.collect(System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(1);
                }
            }

            @Override
            public void cancel() {

            }
        });

        // new source = source1.union(source2)，union可以关联多个
        DataStream<Long> unionStream = stream1.union(stream2).union(stream3);

        SingleOutputStreamOperator<Long> map = unionStream.map((MapFunction<Long, Long>) value -> value);

        // 输出 0, 1, 2, 3, 4, 5 ...
        map.print().setParallelism(1);

        env.execute(UnionStream.class.getSimpleName());
    }
}

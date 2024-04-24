package flinkdemo.c_tramsformer;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.concurrent.TimeUnit;

public class ConnectStream {
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

        DataStreamSource<String> stringDataStreamSource = env.addSource(new SourceFunction<String>() {
            @Override
            public void run(SourceContext<String> sourceContext) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    sourceContext.collect("String" + i);
                    TimeUnit.SECONDS.sleep(1);
                }
            }

            @Override
            public void cancel() {

            }
        });


        ConnectedStreams<Integer, String> connect = integerDataStreamSource.connect(stringDataStreamSource);

        SingleOutputStreamOperator<String> map = connect.map(new CoMapFunction<Integer, String, String>() {
            @Override
            public String map1(Integer integer) throws Exception {
                return "integer to String " + integer;
            }

            @Override
            public String map2(String s) throws Exception {
                return "String to String " + s;
            }
        });

        map.print().setParallelism(1);

        env.execute();
    }
}

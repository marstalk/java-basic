package flinkdemo.a_wordcount2;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


public class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {
    @Override
    public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {
        String[] tokens = value.toLowerCase().split("\\s+");
        for(String token: tokens){
            if (token.length() > 0){
                collector.collect(new Tuple2<>(token, 1));
            }
        }
    }
}

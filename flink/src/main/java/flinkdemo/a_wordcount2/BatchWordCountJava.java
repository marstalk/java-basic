package flinkdemo.a_wordcount2;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.FileSystem;

import java.io.File;

public class BatchWordCountJava {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        System.out.println(new File("").getAbsolutePath());

        String inputDir = "flink/src/main/java/flinkdemo/a_wordcount2/input";
        String outputFile = "flink/src/main/java/flinkdemo/a_wordcount2/output/output.txt";

        DataSource<String> textDataSource = env.readTextFile(inputDir);

        DataSet<Tuple2<String, Integer>> count = textDataSource.flatMap(new Tokenizer())
                //根据tuple的第一个元素做groupby
                .groupBy(0)
                //对tuple的第二元素求和
                .sum(1);

        count.writeAsCsv(outputFile, "\n", " ", FileSystem.WriteMode.OVERWRITE)
                .setParallelism(1); // 并行度为1

        // execute
        env.execute("Batch");
    }
}

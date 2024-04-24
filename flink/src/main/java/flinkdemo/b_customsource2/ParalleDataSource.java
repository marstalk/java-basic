package flinkdemo.b_customsource2;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.concurrent.TimeUnit;

/**
 * 有并行度的自定义source。或implement ParallelSourceFunction 或 extends RichParallelSourceFunction
 *
 * 区别在于Rich提供了open()和close()，做一些准备，比如获取数据库连接等。
 */
public class ParalleDataSource extends RichParallelSourceFunction<Long> {
    private boolean isRunning = true;
    private long count = 1L;

    @Override
    public void run(SourceContext<Long> ctx) throws Exception {
        // 这行代码的执行次数 = 并行度。
        System.out.println("begin");

        while (isRunning) {
            ctx.collect(count++);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("end");
    }

    /**
     * 取消一个cancel的时候会调用的方法。
     */
    @Override
    public void cancel() {
        isRunning = false;
    }
}

package flinkdemo.b_customsource;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.concurrent.TimeUnit;

/**
 * 自定义实现并行度为1的source，模拟产生从1开始递增的数字
 */
public class NoParalleSource implements SourceFunction<Long> {
    private boolean isRunning = true;
    private long count = 1L;
    /**
     * 主要的方法，启动一个source，大部分情况下是用一个循环产生数据。
     * @param ctx
     * @throws Exception 不需要处理异常。
     */
    @Override
    public void run(SourceContext<Long> ctx) throws Exception {
        while (isRunning){
            ctx.collect(count++);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * 取消一个cancel的时候会调用的方法。
     */
    @Override
    public void cancel() {
        isRunning = false;
    }
}

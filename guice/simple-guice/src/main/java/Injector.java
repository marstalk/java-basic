import java.lang.reflect.Type;

/**
 * IOC容器
 */
public interface Injector {
    public <T> T getInstance(Class<T> type);
}

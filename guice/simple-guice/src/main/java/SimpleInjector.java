import java.util.HashMap;
import java.util.Map;

public class SimpleInjector implements Injector{
    private Map<Class<?>, Object> instanceMap = new HashMap<>();
    @Override
    public <T> T getInstance(Class<T> type) {
        Object o = instanceMap.get(type);
        if( o == null){
            instanceMap.put(type, createInstance(type));
            return null;
        }
        return null;
    }

    private <T> T createInstance(Class<T> type){
        return null;
    }

    private void injectDependency(Object obj){
        return;
    }
}

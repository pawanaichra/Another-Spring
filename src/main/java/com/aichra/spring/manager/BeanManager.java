package com.aichra.spring.manager;

import com.aichra.spring.annotations.Autowired;
import com.aichra.spring.annotations.Component;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.util.HashMap;
import java.util.Map;

public class BeanManager {

    private static BeanManager manager = new BeanManager();
    private Map<Class<?>, Object> registry = new HashMap<>();

    private BeanManager() {
        var clazzes = new Reflections(
                "",
                new TypeAnnotationsScanner(),
                new SubTypesScanner())
                .getTypesAnnotatedWith(Component.class);

        for (var clazz : clazzes) {
            createObject(clazz);
        }
    }

    public static BeanManager getInstance() {
        return manager;
    }

    public <T> T getInstance(Class<T> clazz) {
        return clazz.cast(registry.get(clazz));
    }

    public void createObject(Class<?> clazz) {
        if (registry.containsKey(clazz))
            return;

        try {
            var t = clazz.getConstructor().newInstance();
            var fields = clazz.getDeclaredFields();
            for (var field : fields) {
                var autowired = field.getAnnotation(Autowired.class);
                if (autowired != null) {
                    createObject(field.getType());
                    field.setAccessible(true);
                    field.set(t, registry.get(field.getType()));
                }
            }
            registry.put(clazz, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

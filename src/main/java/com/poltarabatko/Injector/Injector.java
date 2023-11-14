package com.poltarabatko.Injector;

import com.poltarabatko.annotations.AutoInjectable;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * The Injector class is responsible for injecting dependencies into an object based on
 * the AutoInjectable annotation and a properties file.
 * @author r.poltarabatko
 */
public class Injector {

    /**
     * Properties object to store key-value pairs from the configuration file.
     */
    private Properties properties;

    /**
     * Default constructor for the Injector class.
     */
    public Injector() {}

    /**
     * Injects dependencies into the given object using the configuration file specified by the path.
     *
     * @param object The object into which dependencies will be injected.
     * @param path   The path to the configuration file containing dependency mappings.
     * @param <T>    The type of the object.
     * @return The object with injected dependencies.
     * @throws IOException If an error occurs while loading the configuration file.
     */
    public <T> T inject(T object, String path) throws IOException {
        loadProperties(path);
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String fieldClassName = field.getType().getName();
                String injectedClassName = properties.getProperty(fieldClassName);

                if (injectedClassName != null) {
                    field.setAccessible(true);

                    try {
                        Class<?> injectedClass = Class.forName(injectedClassName);
                        Object classObject = injectedClass.getDeclaredConstructor().newInstance();
                        field.set(object, classObject);
                    } catch (ClassNotFoundException | InstantiationException |
                             IllegalAccessException | NoSuchMethodException |
                             InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return object;
    }

    /**
     * Loads properties from the specified configuration file.
     *
     * @param path The path to the configuration file.
     * @throws IOException If an error occurs while loading the configuration file.
     */
    private void loadProperties(String path) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IOException("Error loading properties from " + path, e);
        }
    }
}
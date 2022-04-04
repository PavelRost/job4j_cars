package ru.job4j.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class AppService {

    private static final Properties CFG = new Properties();

    public AppService() {
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        AppService.class.getClassLoader()
                                .getResourceAsStream("app.properties")
                )
        )) {
            CFG.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static final class Lazy {
        private static final AppService INST = new AppService();
    }

    public static AppService instOf() {
        return AppService.Lazy.INST;
    }

    public String getPath() {
        return CFG.getProperty("pathToImage");
    }
}

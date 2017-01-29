package ru.stqa.tests.task;

import org.junit.Before;
import ru.stqa.tests.app.Application;

/**
 * Created by Vladimir on 29.01.2017.
 */
public class NewTestBase {


    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }

}


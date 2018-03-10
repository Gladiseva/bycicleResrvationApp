package lv.lollija.bicyclereservation;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        register(new MyApplicationBinder());
        packages(true, "lv.lollija.bicyclereservation");
    }
}

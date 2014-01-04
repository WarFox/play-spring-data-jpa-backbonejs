import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;

/**
 * Application wide behaviour. We establish a Spring application context for the dependency injection system
 */
public class Global extends GlobalSettings {

    /**
     * Declare the application context to be used - a Java annotation based application context requiring no XML.
     */
    final private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    /**
     * Sync the context lifecycle with Play's.
     */
    @Override
    public void onStart(final Application app) {
        super.onStart(app);

        // Scan configs package for annotation based configurations @Configuration
        ctx.scan("configs");

        // AnnotationConfigApplicationContext can only be refreshed once, but we do it here even though this method
        // can be called multiple times. The reason for doing during startup is so that the Play configuration is
        // entirely available to this application context.
        ctx.refresh();

        // This will construct the beans and call any construction lifecycle methods e.g. @PostConstruct
        ctx.start();
    }

    /**
     * Sync the context lifecycle with Play's.
     */
    @Override
    public void onStop(final Application app) {
        // This will call any destruction lifecycle methods and then release the beans e.g. @PreDestroy
        ctx.stop();

        super.onStop(app);
    }

    /**
     * Controllers must be resolved through the application context. There is a special method of GlobalSettings
     * that we can override to resolve a given controller. This resolution is required by the Play router.
     */
    @Override
    public <A> A getControllerInstance(Class<A> aClass) {
        return ctx.getBean(aClass);
    }

}

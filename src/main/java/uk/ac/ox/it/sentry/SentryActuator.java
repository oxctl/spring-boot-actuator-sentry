package uk.ac.ox.it.sentry;

import io.sentry.Sentry;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This provides a simple actuator endpoint to check if Sentry is enabled and to send a test event.
 * To enable this, you need to have the Sentry SDK on the classpath and Spring Boot Actuator.
 * 
 * Note: This does not enable Sentry, it just provides an endpoint to check its status and send a test event.
 */
@ConditionalOnClass(name = {"io.sentry.Sentry", "org.springframework.boot.actuate.endpoint.annotation.Endpoint"})
@Component
@Endpoint(id = "sentry")
public class SentryActuator {

    /**
     * Check if Sentry is enabled.
     * @return A map with a single entry "enabled" which is true if Sentry is enabled, false otherwise.
     */
    @ReadOperation
    public Map<String, Boolean> read() {
        return Map.of("enabled", Sentry.isEnabled());
    }
    
    /**
     * Send a test event to Sentry.
     */
    @WriteOperation
    public void sentry() {
        // This will generate a test event to verify Sentry is working
        Sentry.captureMessage("Sentry is configured correctly");
    }
}

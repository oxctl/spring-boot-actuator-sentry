package uk.ac.ox.it.sentry;

import io.sentry.Sentry;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConditionalOnClass(name = {"io.sentry.Sentry", "org.springframework.boot.actuate.endpoint.annotation.Endpoint"})
@Component
@Endpoint(id = "sentry")
public class SentryActuator {
    
    @ReadOperation
    public Map<String, Boolean> read() {
        return Map.of("enabled", Sentry.isEnabled());
    }
    
    @WriteOperation
    public void sentry() {
        // This will generate a test event to verify Sentry is working
        Sentry.captureMessage("Sentry is configured correctly");
    }
}

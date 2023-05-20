/**
 * 
 */
package br.com.samsung.admin.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.domain.values.StatusInfo;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import reactor.core.publisher.Mono;

/**
 * 
 */
@Component
public class CustomNotifier extends AbstractEventNotifier {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomNotifier.class);

	protected CustomNotifier(InstanceRepository repository) {
		super(repository);
	}

	@Override
	protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
		return Mono.fromRunnable(() -> {
			if (event instanceof InstanceStatusChangedEvent) {
				String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
				if (status.equals(StatusInfo.STATUS_UP) || status.equals(StatusInfo.STATUS_OFFLINE)
						|| status.equals(StatusInfo.STATUS_DOWN)) {
					// Neste ponto, chamar o serviço de notificação.
					System.err.println(instance.getRegistration().getName() + " is " + status);
				}

				LOGGER.info("Instance {} ({}) is {}" + instance.getRegistration().getName(), event.getInstance(),
						status);
			} else {
				LOGGER.info("Instance {} ({}) is {}" + instance.getRegistration().getName(), event.getInstance());
			}
		});
	}

}

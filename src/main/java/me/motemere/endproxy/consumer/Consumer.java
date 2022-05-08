package me.motemere.endproxy.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.logging.Logger;
import me.motemere.endproxy.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  private final MessageService messageService;
  private static final String TOPIC_NAME = "${topic.name}";
  private static final Logger LOG = Logger.getLogger(Consumer.class.getName());

  @Autowired
  public Consumer(MessageService service) {
    this.messageService = service;
  }

  /**
   * Listens to the topic and passes the message to the message service.
   *
   * @param message the message to be processed
   */
  @KafkaListener(topics = TOPIC_NAME, groupId = "default")
  public void consumeMessage(String message) throws JsonProcessingException {
    LOG.info(String.format("Message consumed: %s", message));

    messageService.sendMessageToEntrypoint(message);
  }

}

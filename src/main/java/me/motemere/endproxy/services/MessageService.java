package me.motemere.endproxy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;
import me.motemere.endproxy.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageService {

  private final String entrypointUrl;

  private static final Logger LOG = Logger.getLogger(MessageService.class.getName());

  @Autowired
  public MessageService(@Value("${entrypoint.url}") String url) {
    this.entrypointUrl = url;
  }

  /**
   * Update field in the received message and send it to the entrypoint.
   *
   * @param message The message to update.
   */
  public void sendMessageToEntrypoint(String message) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Message messageObj = objectMapper.readValue(message, Message.class);
    messageObj.setEndProxyTimestamp(System.currentTimeMillis());

    String messageJson = messageObj.toJson();
    LOG.info("Sending message to entrypoint: " + messageJson);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Accept", "*/*");

    HttpEntity<String> requestEntity = new HttpEntity<>(messageJson, headers);
    RestTemplate rest = new RestTemplate();

    ResponseEntity<String> responseEntity = rest.exchange(entrypointUrl, HttpMethod.POST,
        requestEntity, String.class);

    LOG.info("Response code: " + responseEntity.getStatusCode());
    LOG.info("Response from entrypoint: " + responseEntity.getBody());
  }

}

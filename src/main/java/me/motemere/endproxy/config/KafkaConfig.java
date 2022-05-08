package me.motemere.endproxy.config;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {

  /** The model mapper.
   *
   * @return the model mapper.
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}

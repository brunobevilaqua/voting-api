package com.backendtest.voteapi.shared.client;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public abstract class WebClientConfig {

  /** Webclient constructor to be used for open services such as dealer services. */
  public WebClient getBaseClient(String baseUrl) {
    String uuid = UUID.randomUUID().toString();
    Instant start = Instant.now();

    return WebClient.builder()
        .baseUrl(baseUrl)
        .filter(logRequest(uuid))
        .filter(logResponseStatus(uuid, start))
        .build();
  }

  private ExchangeFilterFunction logRequest(String uuid) {
    return ExchangeFilterFunction.ofRequestProcessor(
        request -> {
          log.info(
              "Request: method:{}, url:{}, headers:{}, body:{}, transaction-uuid:{}",
              request.method(),
              request.url(),
              request.headers(),
              request.body(),
              uuid);
          request
              .headers()
              .forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
          return Mono.just(request);
        });
  }

  private ExchangeFilterFunction logResponseStatus(String uuid, Instant startTime) {
    return ExchangeFilterFunction.ofResponseProcessor(
        response -> {
          log.info(
              "Response: http-status:{}, duration:{}ms, trasaction-uuid:{}",
              response.statusCode(),
              Duration.between(startTime, Instant.now()).toMillis(),
              uuid);
          return Mono.just(response);
        });
  }
}

package com.backendtest.voteapi.associate.client;

import com.backendtest.voteapi.associate.AssociateStatus;
import com.backendtest.voteapi.shared.client.WebClientConfig;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoApiClient extends WebClientConfig {
  @Value("${external-services.user-info-api.base-url}")
  private String baseUri;

  @Value("${external-services.user-info-api.operations.validate-user-cpf}")
  private String findUserByCpf;

  public Mono<AssociateStatus> checkUserByCpf(String cpf) {
    return getBaseClient(baseUri)
        .get()
        .uri(buildUri(cpf))
        .exchange()
        .flatMap(
            (clientResponse) -> {
              if (clientResponse.statusCode().value() == HttpStatus.NOT_FOUND.value()) {
                return Mono.just(AssociateStatus.UNABLE_TO_VOTE);
              } else {
                return Mono.just(AssociateStatus.ABLE_TO_VOTE);
              }
            })
        .log();
  }

  private URI buildUri(String cpf) {
    Map<String, String> pathParam = new HashMap<>();
    pathParam.put("cpf", cpf);
    return UriComponentsBuilder.fromUriString(baseUri + findUserByCpf)
        .buildAndExpand(pathParam)
        .toUri();
  }
}

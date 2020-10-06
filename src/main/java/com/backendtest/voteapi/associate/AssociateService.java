package com.backendtest.voteapi.associate;

import com.backendtest.voteapi.associate.client.UserInfoApiClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssociateService {

  private UserInfoApiClient userInfoApiClient;

  public Boolean isAssociateAbleToVote(String cpf) {
    AssociateStatus associateStatus = userInfoApiClient.checkUserByCpf(cpf).block();
    return AssociateStatus.ABLE_TO_VOTE == associateStatus;
  }

}

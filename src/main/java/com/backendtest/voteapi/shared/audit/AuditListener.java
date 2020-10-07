package com.backendtest.voteapi.shared.audit;

import java.time.ZonedDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {

  private static final String ROOT_SYSTEM_DEFAULT = "BACK-END-TEST APPLICATION";

  @PreUpdate
  public void preUpdate(Audit audit) {
    audit.setUpdateDate(ZonedDateTime.now());
  }

  @PrePersist
  public void prePersist(Audit audit) {
    audit.setCreateDate(ZonedDateTime.now());
    audit.setUpdateDate(ZonedDateTime.now());
  }
}

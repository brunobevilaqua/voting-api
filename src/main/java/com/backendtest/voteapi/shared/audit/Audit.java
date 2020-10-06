package com.backendtest.voteapi.shared.audit;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditListener.class)
public class Audit implements Serializable {

  @Column(name = "CREATE_DATE")
  private ZonedDateTime createDate;

  @Column(name = "UPDATE_DATE")
  private ZonedDateTime updateDate;
}

package com.backendtest.voteapi.shared;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * MessageSource Wrapper class.
 */
@Component
@AllArgsConstructor
public class MessageResolverConfig {

  private MessageSource messageSource;

  public String get(String messageKey, Object... params) {
    return messageSource.getMessage(messageKey, params, LocaleContextHolder.getLocale());
  }
}

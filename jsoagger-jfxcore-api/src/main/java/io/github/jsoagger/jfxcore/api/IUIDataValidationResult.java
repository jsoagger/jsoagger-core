package io.github.jsoagger.jfxcore.api;

import java.util.List;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IUIDataValidationResult {

  /**
   * Returns true if has message to display
   *
   * @return boolean
   */
  boolean hasMessage();

  /**
   * Getter of status
   *
   * @return the status
   */
  UIDataValidationResultStatus getStatus();

  /**
   * Setter of status
   *
   * @param status the status to set
   */
  void setStatus(UIDataValidationResultStatus status);

  /**
   * Getter of messages
   *
   * @return the messages
   */
  List<IUIValidationMessage> getMessages();

  /**
   * Setter of messages
   *
   * @param messages the messages to set
   */
  void setMessages(List<IUIValidationMessage> messages);

}

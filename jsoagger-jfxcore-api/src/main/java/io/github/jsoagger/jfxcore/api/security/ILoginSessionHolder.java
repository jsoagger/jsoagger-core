package io.github.jsoagger.jfxcore.api.security;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.jfxcore.api.security.RootContextMode;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface ILoginSessionHolder {

  RootContextMode getMode();

  void setMode(RootContextMode mode);

  /**
   * Getter of sessionId
   *
   * @return the sessionId
   */
  String getSessionId();

  /**
   * Setter of sessionId
   *
   * @param sessionId the sessionId to set
   */
  void setSessionId(String sessionId);

  /**
   * Getter of loginResult
   *
   * @return the loginResult
   */
  IOperationResult getLoginResult();

  /**
   * Setter of loginResult
   *
   * @param loginResult the loginResult to set
   */
  void setLoginResult(IOperationResult loginResult);

  /**
   *
   */
  void logout();
}

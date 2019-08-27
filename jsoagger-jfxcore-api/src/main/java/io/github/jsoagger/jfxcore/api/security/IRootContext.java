package io.github.jsoagger.jfxcore.api.security;

import java.io.Serializable;

import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.jfxcore.api.security.RootContextMode;


public interface IRootContext {

  RootContextMode getMode();

  void setMode(RootContextMode mode);

  void init();

  String getContainerPath();

  OperationData getContainer();

  OperationData getSubject();

  OperationData getAccount();

  String getCurrentSubject();

  /**
   * @{inheritedDoc}
   */
  Serializable getCurrentSessionId();

  /**
   * Getter of loginSessionHolder
   *
   * @return the loginSessionHolder
   */
  ILoginSessionHolder getLoginSessionHolder();

  /**
   * Setter of loginSessionHolder
   *
   * @param loginSessionHolder the loginSessionHolder to set
   */
  void setLoginSessionHolder(ILoginSessionHolder loginSessionHolder);

  /**
   * Getter of containerFullId
   *
   * @return the containerFullId
   */
  String getContainerFullId();

}

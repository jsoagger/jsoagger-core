/**
 *
 */
package io.github.jsoagger.jfxcore.api;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IVLConstraint {

  VLConstraintState getState();

  String getErrorMessage();

  boolean isValid();

  void validate();

  boolean isNotValid();

  void setInput(IComponent input);

  public void setErrorMessageKey(String errorMessageKey);
}

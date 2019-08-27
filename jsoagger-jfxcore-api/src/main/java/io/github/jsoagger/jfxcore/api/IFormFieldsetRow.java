package io.github.jsoagger.jfxcore.api;

import java.util.List;

/**
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IFormFieldsetRow {

  List<ViewInputComponent> getViewEntries();

  void setFieldset(IFieldset owner);

  void beginInlineEdit(IFormRowEditor editor);

  List<IInputComponentWrapper> getEntries();

  void beginEdition();

  IFieldset getFieldset();

  void setLast();

  void endInlineEdit();
}

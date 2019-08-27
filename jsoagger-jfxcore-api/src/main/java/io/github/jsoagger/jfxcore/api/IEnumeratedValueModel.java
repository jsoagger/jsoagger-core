package io.github.jsoagger.jfxcore.api;

import javafx.beans.property.StringPropertyBase;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IEnumeratedValueModel {

  String getSavedValue();

  StringPropertyBase savedValueProperty();

  String getValue();

  String getDescription();

  ObservableValue<? extends String> descriptionProperty();

  void setValue(String string);

  void setSavedValue(String string);

  void setSourceModel(Object data);
}

/**
 *
 */
package io.github.jsoagger.jfxcore.api;

import java.util.List;

import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.util.StringConverter;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IInputComponentWrapper {

  void commitModification();

  void cancelModification();

  VLViewComponentXML getConfiguration();

  IEditInputComponent getEditInputComponent();

  String getCurrentInternalValue();

  String getAttributeName();

  void displayError();

  void validate();

  boolean isNotValid();

  SimpleStringProperty currentInternalValueProperty();

  SimpleStringProperty initialInternalValueProperty();

  boolean isEditing();

  IEnumeratedValueModel getEnumeratedValue(String internalVal);

  String getInitialInternalValue();

  boolean isVisible();

  boolean isLastRow();

  String getAttributePath();

  boolean isMinimized();

  List<IEnumeratedValueModel> getEnumeratedValueModels();

  String getDisplayFormat();

  String getSaveFormat();

  void setCurrentValue(Object newValue);

  IEnumeratedValuesLoader getEnumeratedValuesLoader();

  String getEscapedMultivaluedSeparator();

  String getMultivaluedSeparator();

  StringConverter getConverter();

  VLViewComponentXML configuration();

  IJSoaggerController getController();

  Node getEditLayout();

  void switchToInfoView();

  void switchToEditView();

  ViewInputComponent viewInputComponent();

  Node getDisplay();

  void selectFirsEnumeratedValue();

  IFormFieldsetRow getRow();
}

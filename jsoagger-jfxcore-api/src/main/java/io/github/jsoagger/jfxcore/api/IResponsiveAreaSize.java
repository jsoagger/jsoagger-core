package io.github.jsoagger.jfxcore.api;

import java.util.List;

/**
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IResponsiveAreaSize {

  /**
   * @return
   */
  double getTotalWidth();

  IResponsiveSizing getSizeOf(int colIndex);

  void of(double parentWidth);

  /**
   * Getter of fixedAreaSize
   *
   * @return the fixedAreaSize
   */
  double getFixedAreaSize();

  /**
   * Setter of fixedAreaSize
   *
   * @param fixedAreaSize the fixedAreaSize to set
   */
  void setFixedAreaSize(double fixedAreaSize);

  /**
   * @{inheritedDoc}
   */
  @Override
  int hashCode();

  /**
   * @{inheritedDoc}
   */
  @Override
  boolean equals(Object obj);

  double getMinValue() ;

  void setMinValue(double minValue) ;

  double getMaxValue();

  void setMaxValue(double maxValue);

  List<IResponsiveSizing> getSizes();

  void setSizes(List<IResponsiveSizing> sizes) ;
}

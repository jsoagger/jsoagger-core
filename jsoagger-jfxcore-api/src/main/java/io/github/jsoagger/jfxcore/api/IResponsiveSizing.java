package io.github.jsoagger.jfxcore.api;



public interface IResponsiveSizing {

  /**
   * @param parentWidth2
   * @return
   */
  void of(double parentWidth);

  /**
   * Getter of widthPercent
   *
   * @return the widthPercent
   */
  double getWidthPercent();

  /**
   * Setter of widthPercent
   *
   * @param widthPercent the widthPercent to set
   */
  void setWidthPercent(double widthPercent);

  /**
   * Getter of width
   *
   * @return the width
   */
  double getWidth();

  /**
   * Setter of width
   *
   * @param width the width to set
   */
  void setWidth(double width);

  /**
   * Getter of height
   *
   * @return the height
   */
  double getHeight();

  /**
   * Setter of height
   *
   * @param height the height to set
   */
  void setHeight(double height);

  /**
   * Getter of hide
   *
   * @return the hide
   */
  boolean isToHide();

  /**
   * Setter of hide
   *
   * @param hide the hide to set
   */
  void setHide(boolean hide);

  /**
   * Getter of minimize
   *
   * @return the minimize
   */
  boolean isToMinimize();

  /**
   * Setter of minimize
   *
   * @param minimize the minimize to set
   */
  void setMinimize(boolean minimize);

  /**
   * Getter of parentWidth
   *
   * @return the parentWidth
   */
  double getParentWidth();

  /**
   * Setter of parentWidth
   *
   * @param parentWidth the parentWidth to set
   */
  void setParentWidth(double parentWidth);

  /**
   * Setter of fixedWidth
   *
   * @param fixedWidth the fixedWidth to set
   */
  void setFixedWidth(double fixedWidth);

  /**
   * @{inheritedDoc}
   */
  @Override
  String toString();

  public double getFixedWidth() ;

  public boolean isHide() ;

  public boolean isMinimize() ;

}

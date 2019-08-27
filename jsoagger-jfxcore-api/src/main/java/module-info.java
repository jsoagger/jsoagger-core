/**
 *
 */
/**
 * @author Ramilafananana VONJISOA
 *
 */
module io.github.jsoagger.core.api {
  exports io.github.jsoagger.jfxcore.api;
  exports io.github.jsoagger.jfxcore.api.presenter;
  exports io.github.jsoagger.jfxcore.api.components.annotation;
  exports io.github.jsoagger.jfxcore.api.security;
  exports io.github.jsoagger.jfxcore.api.wizard;
  exports io.github.jsoagger.jfxcore.api.view;
  exports io.github.jsoagger.jfxcore.api.css;
  exports io.github.jsoagger.jfxcore.api.detailsview;
  exports io.github.jsoagger.jfxcore.api.services;
  exports io.github.jsoagger.jfxcore.api.form;

  requires io.github.jsoagger.core.bridge;
  requires io.github.jsoagger.coreview.definition;
  requires javafx.base;
  requires javafx.controls;
  requires javafx.graphics;
}

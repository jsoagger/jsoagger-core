module io.github.jsoagger.core.api {
	exports io.github.jsoagger.jfxcore.api.css;
	exports io.github.jsoagger.jfxcore.api.services;
	exports io.github.jsoagger.jfxcore.api.form;
	exports io.github.jsoagger.jfxcore.api.security;
	exports io.github.jsoagger.jfxcore.api;
	exports io.github.jsoagger.jfxcore.api.detailsview;
	exports io.github.jsoagger.jfxcore.api.components.annotation;
	exports io.github.jsoagger.jfxcore.viewdef.json.xml.model.appcontext;
	exports io.github.jsoagger.jfxcore.viewdef.json.xml.model;
	exports io.github.jsoagger.jfxcore.viewdef.xml.model;
	exports io.github.jsoagger.jfxcore.api.presenter;
	exports io.github.jsoagger.jfxcore.viewdef.json.xml;
	exports io.github.jsoagger.jfxcore.viewdef.xml;
	exports io.github.jsoagger.jfxcore.api.wizard;
	exports io.github.jsoagger.jfxcore.api.view;
	exports io.github.jsoagger.jfxcore.viewdef.xml.model.appcontext;

	requires io.github.jsoagger.core.bridge;
	requires java.xml.bind;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
}
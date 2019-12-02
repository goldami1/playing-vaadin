package com.packagename.myapp.spring.menu.item.component;

import com.vaadin.flow.component.dependency.CssImport;

@CssImport(value = "./styles/toggle-effects-styles.css")
public interface ITogglableAnimatiable
{
	String getToggleEnableClassName();
	String getToggleDisableClassName();
	void toggle();
}

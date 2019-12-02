package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.util.ColorUtils.COLOR;
import com.vaadin.flow.component.HasStyle;

public interface HasInlineStyle extends HasStyle
{
	default void setBackgroundColor(COLOR color)
	{
		setBackgroundColor(color.toString());
	}
	
	default void setBackgroundColor(String colorCode)
	{
		getStyle().set(getBackgroundPropertyName(), colorCode);
	}
	
	default String getBackgroundPropertyName()
	{
		return "backgroundColor";
	}
}

package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.layout.CompositeWrapperVerticalLayout;

public abstract class MenuItem extends CompositeWrapperVerticalLayout implements IMenuItem, HasInlineStyle
{
	private static final long serialVersionUID = 7406209402472994324L;
	
	public MenuItem()
	{
	}
	
	@Override
	public void setBackgroundColor(String colorCode)
	{
		contentWrapper.getStyle().set(getBackgroundPropertyName(), colorCode);
	}
}

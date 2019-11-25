package com.packagename.myapp.spring.menu.item.components;

import com.packagename.myapp.spring.menu.CompositeWrapperLayout;
import com.vaadin.flow.component.html.Div;

public class SeparatorElement extends CompositeWrapperLayout<Div>
{
	private static final long serialVersionUID = 4968812906863388544L;
	
	private SeparatorElement()
	{
		contentWrapper.setWidthFull();
		contentWrapper.setHeight("2px");
		contentWrapper.getStyle().set("background-color", "black");
	}
	
	public static SeparatorElement create()
	{
		return new SeparatorElement();
	}
	
}

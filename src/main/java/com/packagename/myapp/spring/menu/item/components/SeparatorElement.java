package com.packagename.myapp.spring.menu.item.components;

import com.vaadin.flow.component.html.Div;

public class SeparatorElement extends CustomComponents<Div>
{
	private static final long serialVersionUID = 4968812906863388544L;
	
	private SeparatorElement()
	{
		contentWrapper.setWidthFull();
		contentWrapper.setClassName("separator-style");
	}
	
	public static SeparatorElement create()
	{
		return new SeparatorElement();
	}
}

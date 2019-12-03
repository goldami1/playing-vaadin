package com.packagename.myapp.spring.menu.item.component;

import com.vaadin.flow.component.html.Div;

public class SeparatorElement extends CustomComponents<Div>
{
	private static final long serialVersionUID = 4968812906863388544L;
	private static final String separatorStyleClassName = "separator-style";
	
	private SeparatorElement()
	{
		contentWrapper.setWidthFull();
		contentWrapper.setClassName(separatorStyleClassName);
	}
	
	public static SeparatorElement create()
	{
		return new SeparatorElement();
	}
}

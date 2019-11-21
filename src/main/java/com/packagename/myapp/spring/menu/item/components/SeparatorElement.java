package com.packagename.myapp.spring.menu.item.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;

public class SeparatorElement extends Composite<Div>
{
	private static final long serialVersionUID = 4968812906863388544L;
	
	private SeparatorElement()
	{
		getContent().setWidthFull();
		getContent().setHeight("2px");
		getContent().getStyle().set("background-color", "black");
	}
	
	public static SeparatorElement create()
	{
		return new SeparatorElement();
	}
	
}

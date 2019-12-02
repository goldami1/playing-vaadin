package com.packagename.myapp.spring.layout;

import com.github.appreciated.IronCollapse;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.model.style.Color;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class IronCollapseLayout extends CompositeWrapperLayout<IronCollapse>
{
	private static final long serialVersionUID = 5769340501626852751L;
	private VerticalLayout wrapper;
	
	public IronCollapseLayout()
	{
		wrapper = new VerticalLayout();
		wrapper.setPadding(false);
		wrapper.setSpacing(false);
		wrapper.setMargin(false);
		wrapper.setWidthFull();
		
		contentWrapper.add(wrapper);
		contentWrapper.setWidthFull();
	}
	
	public IronCollapseLayout(Color backgroundColor)
	{
		this();
		contentWrapper.getElement().getStyle().set("background-color", backgroundColor.toString());
	}
	
	public void add(Component component)
	{
		wrapper.add(component);
	}
	
	public void toggle()
	{
		contentWrapper.toggle();
	}
}

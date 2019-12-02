package com.packagename.myapp.spring.menu.item.components;

import java.io.Serializable;

import com.packagename.myapp.spring.menu.CompositeWrapperLayout;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.Div;

public abstract class TogglableComponent<T extends Component & HasStyle & ClickNotifier<? extends Component>> extends CompositeWrapperLayout<Div> implements ITogglableAnimatiable, Serializable
{
	private static final long serialVersionUID = 449464354512510170L;
	private boolean toggleStatus;
	private T component;
	
	private TogglableComponent()
	{
		toggleStatus = false;
		contentWrapper.setSizeUndefined();
	}
	
	public TogglableComponent(T component)
	{
		this();
		contentWrapper.addClickListener(e -> toggle());
		contentWrapper.add(component);
		this.component = component;
	}
	
	@Override
	public void toggle()
	{
		if(toggleStatus)
		{
			component.addClassName(getToggleEnableClassName());
			component.removeClassName(getToggleDisableClassName());
		}
		else
		{
			component.addClassName(getToggleDisableClassName());
			component.removeClassName(getToggleEnableClassName());
		}
		
		toggleStatus = !toggleStatus;
	}
}

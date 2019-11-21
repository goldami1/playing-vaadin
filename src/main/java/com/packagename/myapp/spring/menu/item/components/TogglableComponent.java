package com.packagename.myapp.spring.menu.item.components;

import java.io.Serializable;
import java.util.Optional;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.Div;

public abstract class TogglableComponent<T extends Component & HasStyle & ClickNotifier<? extends Component>> extends Composite<Div> implements ITogglableAnimatiable, Serializable
{
	private static final long serialVersionUID = 449464354512510170L;
	private boolean toggleStatus;
	private Div wrapper;
	private T component;
	
	private TogglableComponent()
	{
		wrapper = getContent();
		toggleStatus = false;
		wrapper.setSizeUndefined();
	}
	
	public TogglableComponent(T component)
	{
		this();
		Optional.ofNullable(component).get().addClickListener(e -> toggle());
		wrapper.add(component);
		this.component = component;
	}
	
	public void activateHighlightOnHover()
	{
		component.addClassName("icon-item-focused");
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

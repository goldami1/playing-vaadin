package com.packagename.myapp.spring.menu.item.components;

import java.util.Optional;

import com.vaadin.flow.component.icon.Icon;

public class TogglableActionIcon extends TogglableActionComponent<Icon>
{
	private static final long serialVersionUID = 9058607056242901365L;
	private String toggleEnableClassName;
	private String toggleDisableClassName;
	
	public TogglableActionIcon(String toggleEnableClassName, String toggleDisableClassName,
								Icon component, Runnable toggleAction)
	{
		super(component, toggleAction);
		this.toggleEnableClassName = Optional.ofNullable(toggleEnableClassName).get();;
		this.toggleDisableClassName = Optional.ofNullable(toggleDisableClassName).get();;
	}
	
	@Override
	public String getToggleEnableClassName()
	{
		return toggleEnableClassName;
	}

	@Override
	public String getToggleDisableClassName()
	{
		return toggleDisableClassName;
	}
}

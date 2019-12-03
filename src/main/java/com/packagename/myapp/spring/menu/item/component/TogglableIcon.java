package com.packagename.myapp.spring.menu.item.component;

import java.util.Optional;

import com.vaadin.flow.component.icon.Icon;

public class TogglableIcon extends TogglableComponent<Icon>
{
	private static final long serialVersionUID = 7679982719980886084L;
	private String toggleEnableClassName;
	private String toggleDisableClassName;
	
	public TogglableIcon(String toggleEnableClassName, String toggleDisableClassName,
						Icon component)
	{
		super(component);
		Optional.ofNullable(component).get().addClickListener(e -> toggleAnimation());
		this.toggleEnableClassName = Optional.ofNullable(toggleEnableClassName).get();
		this.toggleDisableClassName = Optional.ofNullable(toggleDisableClassName).get();
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

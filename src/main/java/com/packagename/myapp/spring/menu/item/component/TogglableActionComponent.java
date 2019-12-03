package com.packagename.myapp.spring.menu.item.component;

import java.util.Optional;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;

public abstract class TogglableActionComponent<T extends Component & HasStyle & ClickNotifier<? extends Component>> extends TogglableComponent<T> implements ITogglableActionable
{
	private static final long serialVersionUID = 1398838713000356903L;
	private Runnable toggleAction;
	
	public TogglableActionComponent(T component, Runnable toggleAction)
	{
		super(component);
		this.toggleAction = Optional.ofNullable(toggleAction).get();
		component.addClickListener(e -> activateOnToggle());
	}
	
	@Override
	public void activateOnToggle()
	{
		ITogglableActionable.super.activateOnToggle();
		toggleAnimation();
	}

	@Override
	public Runnable getToggleAction()
	{
		return toggleAction;
	}
}

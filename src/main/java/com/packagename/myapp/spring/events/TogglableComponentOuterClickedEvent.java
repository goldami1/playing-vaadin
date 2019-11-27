package com.packagename.myapp.spring.events;

import com.packagename.myapp.spring.menu.item.components.TogglableComponent;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.HasStyle;

public class TogglableComponentOuterClickedEvent<T extends Component & HasStyle & ClickNotifier<? extends Component>> extends ComponentEvent<TogglableComponent<T>>
{
	public TogglableComponentOuterClickedEvent(TogglableComponent<T> source, boolean fromClient)
	{
		super(source, fromClient);
	}

	private static final long serialVersionUID = 9050661455462500952L;
	
}

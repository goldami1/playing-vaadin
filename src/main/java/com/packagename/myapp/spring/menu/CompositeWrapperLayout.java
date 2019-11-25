package com.packagename.myapp.spring.menu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;

public abstract class CompositeWrapperLayout<T extends Component> extends Composite<T> {

	private static final long serialVersionUID = -5912336733031803111L;
	protected T contentWrapper;
	
	public CompositeWrapperLayout()
	{
		this.contentWrapper = getContent();
	}
}

package com.packagename.myapp.spring.layout;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class CompositeWrapperVerticalLayout extends CompositeWrapperLayout<VerticalLayout>
{
	private static final long serialVersionUID = 1951815846756579817L;
	
	public CompositeWrapperVerticalLayout()
	{
		contentWrapper.setSpacing(false);
		contentWrapper.setPadding(false);
		contentWrapper.setMargin(false);
	}
}

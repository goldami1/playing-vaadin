package com.packagename.myapp.spring.menu;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class CompositeWrapperHorizontalLayout extends CompositeWrapperLayout<HorizontalLayout>
{
	private static final long serialVersionUID = 1260061890517921011L;
	
	public CompositeWrapperHorizontalLayout()
	{
		contentWrapper.setSpacing(false);
		contentWrapper.setPadding(false);
		contentWrapper.setMargin(false);
	}
}

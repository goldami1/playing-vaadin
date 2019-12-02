package com.packagename.myapp.spring.view;

import com.packagename.myapp.spring.layout.CompositeWrapperVerticalLayout;
import com.packagename.myapp.spring.menu.item.component.CustomTitleLabel;
import com.packagename.myapp.spring.menu.item.component.CustomTitleLabel.TitleType;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

public class GenericView extends CompositeWrapperVerticalLayout
{
	private static final long serialVersionUID = 7856740166389913391L;

	public GenericView(String viewName)
	{
		contentWrapper.setSizeFull();
		contentWrapper.add(new CustomTitleLabel("View: "+viewName, TitleType.TITLE));
		contentWrapper.setAlignItems(Alignment.CENTER);
		contentWrapper.setJustifyContentMode(JustifyContentMode.CENTER);
	}
}

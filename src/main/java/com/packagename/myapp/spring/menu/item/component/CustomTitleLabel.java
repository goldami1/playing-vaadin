package com.packagename.myapp.spring.menu.item.component;

import com.packagename.myapp.spring.layout.CompositeWrapperLayout;
import com.vaadin.flow.component.html.Label;

public class CustomTitleLabel extends CompositeWrapperLayout<Label>
{
	private static final long serialVersionUID = 7874763873484882166L;
	public enum TitleType{TITLE, SUBTITLE};

	public CustomTitleLabel(String title, TitleType titleType)
	{
		contentWrapper.setText(title);
		
		switch(titleType)
		{
		case TITLE:
			contentWrapper.setClassName("custom-label-title-style");
			break;
		case SUBTITLE:
			contentWrapper.setClassName("custom-label-subtitle-style");
			break;
		default:
			break;
		}
	}
}

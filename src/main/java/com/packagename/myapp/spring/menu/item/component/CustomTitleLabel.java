package com.packagename.myapp.spring.menu.item.component;

import com.packagename.myapp.spring.layout.CompositeWrapperLayout;
import com.vaadin.flow.component.html.Label;

public class CustomTitleLabel extends CompositeWrapperLayout<Label>
{
	private static final long serialVersionUID = 7874763873484882166L;
	private static final String titleLabelStyleClassName = "custom-label-title-style";
	private static final String subtitleLabelStyleClassName = "custom-label-subtitle-style";
	
	public enum TitleType{TITLE, SUBTITLE};

	public CustomTitleLabel(String title, TitleType titleType)
	{
		contentWrapper.setText(title);
		
		switch(titleType)
		{
		case TITLE:
			contentWrapper.setClassName(titleLabelStyleClassName);
			break;
		case SUBTITLE:
			contentWrapper.setClassName(subtitleLabelStyleClassName);
			break;
		default:
			break;
		}
	}
}

package com.packagename.myapp.spring.view;

import com.packagename.myapp.spring.layout.CompositeWrapperVerticalLayout;
import com.packagename.myapp.spring.menu.item.component.CustomTitleLabel;
import com.packagename.myapp.spring.menu.item.component.CustomTitleLabel.TitleType;

public class GenericView extends CompositeWrapperVerticalLayout
{
	private static final long serialVersionUID = 7856740166389913391L;

	public GenericView(int num)
	{
		contentWrapper.add(new CustomTitleLabel("View No. "+num, TitleType.TITLE));
	}
}

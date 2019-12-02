package com.packagename.myapp.spring.menu.item;

import java.util.LinkedList;
import java.util.List;

import com.github.appreciated.IronCollapse;
import com.packagename.myapp.spring.layout.CompositeWrapperLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SubmenuLayout extends CompositeWrapperLayout<IronCollapse>
{
	private static final long serialVersionUID = -1314770404079599694L;
	private VerticalLayout submenuLayout;
	private List<IMenuItem> submenuItems;
	
	public SubmenuLayout()
	{
		submenuItems = new LinkedList<>();
		submenuLayout = new VerticalLayout();
		submenuLayout.setPadding(false);
		submenuLayout.setMargin(false);
		submenuLayout.setSpacing(false);
		submenuLayout.setWidthFull();
	}
	
	public void toggle()
	{
		contentWrapper.toggle();
	}
	
	public void addMenuEntry(BodyMenuItem menuEntry)
	{
		submenuItems.add(menuEntry);
		contentWrapper.add(menuEntry);
	}

}

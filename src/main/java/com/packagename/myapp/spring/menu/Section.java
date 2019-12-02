package com.packagename.myapp.spring.menu;

import java.util.LinkedList;
import java.util.List;

import com.packagename.myapp.spring.layout.CompositeWrapperVerticalLayout;
import com.packagename.myapp.spring.menu.item.MenuItem;

public abstract class Section extends CompositeWrapperVerticalLayout
{
	private static final long serialVersionUID = 7267063486595409899L;

	public enum SectionType{HEADER,BODY};
	private SectionType sectionType;
	private List<MenuItem> menuItems;
	
	protected Section(SectionType sectionType)
	{
		this.sectionType = sectionType;
		menuItems = new LinkedList<>();
	}
	
	public void addItem(MenuItem item)
	{
		menuItems.add(item);
		contentWrapper.add(item);
	}
	
	public SectionType getSectionType()
	{
		return sectionType;
	}
	
}

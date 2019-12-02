package com.packagename.myapp.spring.menu;

import java.util.LinkedList;
import java.util.List;

import com.packagename.myapp.spring.layout.MenuLayout;
import com.packagename.myapp.spring.menu.Section.SectionType;

public class Menu extends MenuLayout
{
	private static final long serialVersionUID = -4214876491992354733L;
	private List<Section> sections;
	
	private Menu()
	{
		sections = new LinkedList<>();
	}
	
	public static Menu create(List<Section> sections)
	{
		Menu menu = new Menu();
		
		sections.stream().filter(sec -> sec.getSectionType() == SectionType.HEADER).forEach(sec -> menu.addSection(sec));
		sections.stream().filter(sec -> sec.getSectionType() == SectionType.BODY).forEach(sec -> menu.addSection(sec));
		
		menu.build();
		
		return menu;
	}
	
	private void build()
	{
		
	}

	public Menu addSection(Section section)
	{
		sections.add(section);
		add(section);
		return this;
	}
}

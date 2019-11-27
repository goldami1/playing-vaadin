package com.packagename.myapp.spring.menu.item;

import java.util.LinkedList;
import java.util.List;

import com.packagename.myapp.spring.menu.IronCollapseLayout;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.icon.Icon;

public class BodyMenuItem extends MenuItem implements ClickNotifier<BodyMenuItem>
{
	private static final long serialVersionUID = 3351314682843165886L;

	private MenuEntry menuEntry;
	
	private boolean submenu;
	private IronCollapseLayout submenuLayout;
	private List<BodyMenuItem> submenuItems;
	
	public BodyMenuItem(Icon menuEntryIcon, String menuEntryTitle)
	{
		contentWrapper.setWidthFull();
		submenu = false;
		
		menuEntry = new MenuEntry(menuEntryIcon, menuEntryTitle);
		contentWrapper.add(menuEntry);
	}
	
	private void initAsSubmenu()
	{
		if(submenu)
			return;
		
		submenu = !submenu;
		submenuItems = new LinkedList<>();
		submenuLayout = new IronCollapseLayout();
		contentWrapper.add(submenuLayout);
		menuEntry.addSubmenuToggleIcon();
		menuEntry.addMenuItemClickListener(e ->
		{
			submenuLayout.toggle();
		});
	}
	
	public BodyMenuItem addSubItem(BodyMenuItem bodyMenuItem)
	{
		initAsSubmenu();
		submenuItems.add(bodyMenuItem);
		submenuLayout.add(bodyMenuItem);
		
		return this;
	}
}

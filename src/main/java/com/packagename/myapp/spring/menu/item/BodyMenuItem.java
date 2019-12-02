package com.packagename.myapp.spring.menu.item;

import java.util.LinkedList;
import java.util.List;

import com.packagename.myapp.spring.layout.IronCollapseLayout;
import com.packagename.myapp.spring.util.ColorUtils;
import com.packagename.myapp.spring.util.ColorUtils.COLOR;
import com.packagename.myapp.spring.util.ColorUtils.RGBColorModel;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.icon.Icon;

public class BodyMenuItem extends MenuItem implements ClickNotifier<BodyMenuItem>
{
	private static final long serialVersionUID = 3351314682843165886L;

	private MenuEntry menuEntry;
	
	private boolean submenu;
	private int menuHierarchyLevel;
	private static final int COLOR_MULTIPLICATION_CONST = 25;
	private static final String defaultBackgroundColor = COLOR.WHITESMOKE.toString();
	private IronCollapseLayout submenuLayout;
	private List<BodyMenuItem> submenuItems;
	
	
	public BodyMenuItem(Icon menuEntryIcon, String menuEntryTitle, Runnable toggleAction)
	{
		setBackgroundColor(RGBColorModel.create(defaultBackgroundColor).setOpacity(0.5f).toString());
		contentWrapper.setWidthFull();
		
		submenu = false;
		menuHierarchyLevel = 0;
		
		contentWrapper.add(menuEntry = MenuEntry.create(menuEntryIcon, menuEntryTitle, toggleAction));
	};
	
	public BodyMenuItem setHierarchyLevel(int menuHierarchyLevel)
	{
		this.menuHierarchyLevel = menuHierarchyLevel;
		setBackgroundColor(evaluateBackgroundColor().toString());
		return this;
	}
	
	public BodyMenuItem addSubItem(BodyMenuItem bodyMenuItem)
	{
		bodyMenuItem.setHierarchyLevel(menuHierarchyLevel+1);
		
		initAsSubmenu();
		submenuItems.add(bodyMenuItem);
		submenuLayout.add(bodyMenuItem);
		
		return this;
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
		menuEntry.addMenuItemClickListener(e -> submenuLayout.toggle());
	}
	
	private RGBColorModel evaluateBackgroundColor()
	{
		return ColorUtils.darkenColor(RGBColorModel.create(contentWrapper.getStyle().get(getBackgroundPropertyName())).setOpacity(0.5f), 100-menuHierarchyLevel*COLOR_MULTIPLICATION_CONST);
	}
}

package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.menu.item.components.TogglableIcon;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MenuEntry extends MenuItem implements ClickNotifier<MenuEntry>
{
	private static final long serialVersionUID = 6561923337482901458L;
	private Label menuEntryTitle;
	private Icon menuEntryIcon;
	private HorizontalLayout menuEntryWrapper;
	private TogglableIcon submenuDropdownIcon;
	
	private MenuEntry()
	{
		menuEntryWrapper = new HorizontalLayout();
		menuEntryWrapper.setPadding(false);
		menuEntryWrapper.setMargin(false);
		menuEntryWrapper.setSpacing(false);
		menuEntryWrapper.setWidthFull();
		
		contentWrapper.add(menuEntryWrapper);
	}
	
	public MenuEntry(Icon menuEntryIcon, String menuEntryTitle)
	{
		this();
		
		menuEntryWrapper.addClassName("menu-item-style");
		HorizontalLayout iconAndLabelLayout = createIconTitleLayout(menuEntryIcon, menuEntryTitle);
		menuEntryWrapper.add(iconAndLabelLayout);
	}


	public void addSubmenuToggleIcon()
	{
		submenuDropdownIcon = new TogglableIcon("icon-item-rotation-toggle-enabled",
											"icon-item-rotation-toggle-disabled",
											VaadinIcon.CHEVRON_DOWN_SMALL.create());
		
		FlexLayout iconWrapper = new FlexLayout(submenuDropdownIcon);
		
		iconWrapper.setJustifyContentMode(JustifyContentMode.END);
		menuEntryWrapper.expand(iconWrapper);
		menuEntryWrapper.add(iconWrapper);
		
		menuEntryWrapper.addClickListener(e -> fireEvent(new MenuEntryClickedEvent(this, false)));
	}
	
	private HorizontalLayout createIconTitleLayout(Icon menuEntryIcon, String menuEntryTitle)
	{
		HorizontalLayout iconAndLabelLayout = new HorizontalLayout();
		
		this.menuEntryTitle = new Label(menuEntryTitle);
		this.menuEntryTitle.addClassName("menu-item-text-style");
		this.menuEntryIcon = menuEntryIcon;
		
		iconAndLabelLayout.add(this.menuEntryIcon, this.menuEntryTitle);
		
		return iconAndLabelLayout;
	}
	
	public Registration addMenuItemClickListener(ComponentEventListener<MenuEntryClickedEvent> listener)
	{
		return addListener(MenuEntryClickedEvent.class, new ComponentEventListener<MenuEntry.MenuEntryClickedEvent>()
		{
			private static final long serialVersionUID = -8108388415081227841L;

			@Override
			public void onComponentEvent(MenuEntryClickedEvent event)
			{
				listener.onComponentEvent(event);
				if(submenuDropdownIcon != null)
				{
					submenuDropdownIcon.toggle();
				}
			}
		});
	}
	
	public static class MenuEntryClickedEvent extends ComponentEvent<MenuEntry>
	{
		public MenuEntryClickedEvent(MenuEntry source, boolean fromClient) {
			super(source, fromClient);
		}

		private static final long serialVersionUID = 9050661455462500952L;
		
	}
}

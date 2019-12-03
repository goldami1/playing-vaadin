package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.menu.item.component.TogglableActionComponent;
import com.packagename.myapp.spring.menu.item.component.TogglableIcon;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MenuEntry extends TogglableActionComponent<Div> implements IMenuItem
{
	private static final long serialVersionUID = 6561923337482901458L;
	
	private Label menuEntryTitle;
	private Icon menuEntryIcon;
	private HorizontalLayout menuEntryWrapper;
	private TogglableIcon submenuDropdownIcon;
	
	private MenuEntry(Div component, Runnable toggleAction)
	{
		super(component, toggleAction);
	}
	
	public static MenuEntry create(Icon menuEntryIcon, String menuEntryTitle, Runnable toggleAction)
	{
		MenuEntry menuEntry = new MenuEntry(new Div(), toggleAction);
		menuEntry.init(menuEntryIcon, menuEntryTitle);
		menuEntry.contentWrapper.setWidthFull();
		
		return menuEntry;
	}
	
	private void init(Icon menuEntryIcon, String menuEntryTitle)
	{
		menuEntryWrapper = new HorizontalLayout();
		menuEntryWrapper.setPadding(false);
		menuEntryWrapper.setMargin(false);
		menuEntryWrapper.setSpacing(false);
		menuEntryWrapper.addClickListener(e -> fireEvent(new MenuEntryClickedEvent(this, false)));
		
		menuEntryWrapper.addClassName("menu-item-style");
		HorizontalLayout iconAndLabelLayout = createIconTitleLayout(menuEntryIcon, menuEntryTitle);
		menuEntryWrapper.add(iconAndLabelLayout);
		addMenuItemClickListener(e -> activateOnToggle());
		
		contentWrapper.add(menuEntryWrapper);
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
		
		menuEntryWrapper.addClickListener(e -> submenuDropdownIcon.toggleAnimation());
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
		return addListener(MenuEntryClickedEvent.class, listener);
	}
	
	public static class MenuEntryClickedEvent extends ComponentEvent<MenuEntry>
	{
		private static final long serialVersionUID = 9050661455462500952L;
		
		public MenuEntryClickedEvent(MenuEntry source, boolean fromClient) {
			super(source, fromClient);
		}
	}

	@Override
	public String getToggleEnableClassName() {
		return "n";
	}

	@Override
	public String getToggleDisableClassName() {
		return "n";
	}
}

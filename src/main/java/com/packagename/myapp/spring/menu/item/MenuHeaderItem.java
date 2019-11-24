package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.menu.item.components.TogglableActionIcon;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

public abstract class MenuHeaderItem extends HeaderItem<HorizontalLayout>
{
	private static final long serialVersionUID = -1208289264314494270L;
	
	public MenuHeaderItem()
	{
		HorizontalLayout rowLayout = createRowLayout();
		
		Div menuComponent = createMenuComponent();
		rowLayout.add(menuComponent);
		
		Div logoComponent = createLogoComponent();
		FlexLayout wrapper = new FlexLayout(logoComponent);
		wrapper.setJustifyContentMode(JustifyContentMode.CENTER);
		
		rowLayout.expand(wrapper);
		rowLayout.add(wrapper);
		
		addHeaderRow(rowLayout);
	}
	
	public abstract Div createLogoComponent();

	private Div createMenuComponent()
	{
		return new Div(new TogglableActionIcon("icon-item-rotation-toggle-enabled",
				"icon-item-rotation-toggle-disabled", VaadinIcon.MENU.create(), () -> toggleMenuState()));
	}
	
	private void toggleMenuState()
	{
		fireEvent(new MenuComponentClickedEvent(this, false));
	}

	public class MenuComponentClickedEvent extends ComponentEvent<MenuHeaderItem>
	{
		private static final long serialVersionUID = -2880318393040733464L;

		public MenuComponentClickedEvent(MenuHeaderItem source, boolean fromClient)
		{
			super(source, fromClient);
		}
	}
}

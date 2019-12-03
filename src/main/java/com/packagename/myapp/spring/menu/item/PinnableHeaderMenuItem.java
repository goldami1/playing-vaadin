package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.menu.item.component.TogglableActionIcon;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

public abstract class PinnableHeaderMenuItem extends HeaderMenuItem<HorizontalLayout>
{
	private static final long serialVersionUID = -1208289264314494270L;
	private static final String menuIconEffectToggleStyleClassName = "menu-item-icon-rotation";
	private static final String menuIconEffectRetoggleStyleClassName = "menu-item-icon-rotation-toggled";
	
	
	public PinnableHeaderMenuItem()
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
	
	public Registration addMenuOnClickListener(ComponentEventListener<MenuComponentClickedEvent> menuButtonClickedListener)
	{
		return addListener(MenuComponentClickedEvent.class, menuButtonClickedListener);
	}

	private Div createMenuComponent()
	{
		return new Div(new TogglableActionIcon(menuIconEffectToggleStyleClassName,
												menuIconEffectRetoggleStyleClassName,
												VaadinIcon.PIN.create(), () -> toggleMenuState()));
	}
	
	private void toggleMenuState()
	{
		fireEvent(new MenuComponentClickedEvent(this, false));
	}

	public class MenuComponentClickedEvent extends ComponentEvent<PinnableHeaderMenuItem>
	{
		private static final long serialVersionUID = -2880318393040733464L;

		public MenuComponentClickedEvent(PinnableHeaderMenuItem source, boolean fromClient)
		{
			super(source, fromClient);
		}
	}
}

package com.packagename.myapp.spring.menu.item;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class HeaderItem<T extends Component & ClickNotifier<? extends Component>> extends Composite<VerticalLayout>
{
	private static final long serialVersionUID = 7717172652743246530L;
	private VerticalLayout verticalWrapper;
	private HorizontalLayout horizontalWrapper;
	
	public HeaderItem()
	{
		verticalWrapper = getContent();
		verticalWrapper.setPadding(false);
		verticalWrapper.getStyle().set("margin-bottom", "20px");
		verticalWrapper.setSpacing(false);
		horizontalWrapper = new HorizontalLayout();
		horizontalWrapper.setPadding(false);
		horizontalWrapper.setMargin(false);
		horizontalWrapper.setSpacing(false);
	}
	
	
}

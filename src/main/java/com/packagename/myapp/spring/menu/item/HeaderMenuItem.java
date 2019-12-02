package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.menu.item.component.SeparatorElement;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class HeaderMenuItem<T extends Component> extends MenuItem
{
	private static final long serialVersionUID = 7717172652743246530L;
	
	public HeaderMenuItem()
	{
		createHeaderWrapper();
		createHeaderSeparator();
	}
	
	public void addHeaderRow(T component)
	{
		HorizontalLayout rowLayout = createRowLayout();
		
		rowLayout.setDefaultVerticalComponentAlignment(Alignment.START);
		rowLayout.getStyle().set("user-select", "none");
							
		rowLayout.add(component);
		contentWrapper.add(rowLayout);
	}
	
	private void createHeaderSeparator()
	{
		Component headerSeparator = SeparatorElement.create();
		headerSeparator.getElement().getStyle().set("order", Integer.MAX_VALUE+"");
		
		contentWrapper.add(headerSeparator);
	}

	private void createHeaderWrapper()
	{
		contentWrapper.setWidthFull();
		contentWrapper.getStyle().set("margin-bottom", "20px");
	}
	
	protected HorizontalLayout createRowLayout()
	{
		HorizontalLayout horizontalWrapper = new HorizontalLayout();
		horizontalWrapper.setPadding(false);
		horizontalWrapper.setMargin(false);
		horizontalWrapper.setSpacing(false);
		horizontalWrapper.setWidthFull();
		
		return horizontalWrapper;
	}
}

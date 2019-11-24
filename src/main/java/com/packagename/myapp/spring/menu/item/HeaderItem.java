package com.packagename.myapp.spring.menu.item;

import com.packagename.myapp.spring.menu.item.components.SeparatorElement;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class HeaderItem<T extends Component> extends Composite<VerticalLayout> implements IMenuItem
{
	private static final long serialVersionUID = 7717172652743246530L;
	private VerticalLayout verticalWrapper;
	
	public HeaderItem()
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
		verticalWrapper.add(rowLayout);
	}
	
	private void createHeaderSeparator()
	{
		Component headerSeparator = SeparatorElement.create();
		headerSeparator.getElement().getStyle().set("order", Integer.MAX_VALUE+"");
		
		verticalWrapper.add(headerSeparator);
	}

	private void createHeaderWrapper()
	{
		verticalWrapper = getContent();
		verticalWrapper.setPadding(false);
		verticalWrapper.setWidthFull();
		verticalWrapper.setSpacing(false);
		verticalWrapper.getStyle().set("margin-bottom", "20px");
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

package com.packagename.myapp.spring.menu.item.component;

import com.packagename.myapp.spring.layout.CompositeWrapperLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport(value = "./styles/custom-component-styles.css")
public class CustomComponents<T extends Component> extends CompositeWrapperLayout<T>
{
	private static final long serialVersionUID = 2986121564417552280L;
	
	
}

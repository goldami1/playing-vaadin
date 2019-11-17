package com.packagename.myapp.spring;

import java.util.LinkedList;
import java.util.List;

import com.github.appreciated.IronCollapse;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route(value = "")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@CssImport(value = "./styles/SpecificStyles.css")
public class DefaultView extends HorizontalLayout
{
	private FlexLayout menu;
	private Div content;
	private List<String> submenuColors;
	
	public DefaultView()
	{
		submenuColors = new LinkedList<>();
		submenuColors.add("#fefecc");
		submenuColors.add("#feff99");
		submenuColors.add("#feff65");
		submenuColors.add("#feff32");
		submenuColors.add("#feff00");
		submenuColors.add("#cbcc00");
		this.setSizeFull();
		menu = new FlexLayout();
		menu.setHeightFull();
		menu.getStyle().set("flex-direction", "column");
		menu.addClassName("moving");
		content = new Div();
		content.getStyle().set("margin", "0px");
		content.setSizeFull();
		content.getStyle().set("background-color", "#98c3f5");
		
		add(menu, content);
		setFlexGrow(1, content);
		Button button1 = new Button("Menu1");
		Button button2 = new Button("Menu2");
		
		VerticalLayout submenuLayout = new VerticalLayout();
		submenuLayout.setPadding(false);
		submenuLayout.getStyle().set("background-color", "#feff99");
		
		for(int i=1;i<=5;i++)
		{
			Button submenuButton = new Button("SubMenu1."+i);
			submenuButton.setWidthFull();
			submenuLayout.add(submenuButton);
		}
		
        IronCollapse collapsibleSubmenu = new IronCollapse(submenuLayout);
        collapsibleSubmenu.setWidthFull();
        
        
        
		button1.setWidthFull();
		button2.setWidthFull();
		
		button1.addClickListener(e ->
		{
			collapsibleSubmenu.toggle();
		});
		
		button2.addClickListener(e -> Notification.show("hello"));
		
		Label textToAdd = new Label("M");
		textToAdd.getStyle().set("font-size", "xx-large");
		textToAdd.getStyle().set("font-style", "bold");
		textToAdd.getStyle().set("padding-left", "9px");
		menu.add(textToAdd);
		textToAdd = new Label("ver. 3.0");
		textToAdd.getStyle().set("font-size", "xx-small");
		textToAdd.getStyle().set("padding-left", "9px");
		menu.add(textToAdd);
		menu.add(button1);
		menu.add(collapsibleSubmenu);
		menu.add(button2);
		
		this.setAlignItems(Alignment.STRETCH);
		this.expand(menu);
	}
}

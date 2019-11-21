package com.packagename.myapp.spring;

import java.util.LinkedList;
import java.util.List;

import com.github.appreciated.IronCollapse;
import com.packagename.myapp.spring.menu.item.components.SeparatorElement;
import com.packagename.myapp.spring.menu.item.components.TogglableActionIcon;
import com.packagename.myapp.spring.menu.item.components.TogglableIcon;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route(value = "")
@CssImport(value = "./styles/SpecificStyles.css")
public class DefaultView extends HorizontalLayout
{
	private MenuLayout menu;
	private Div content;
	private List<String> submenuColors;
	private enum MenuDisplayType {EXTENDED,COLLAPSED,DYNAMIC};
	private MenuDisplayType menuDisplayType = MenuDisplayType.DYNAMIC;
	
	public DefaultView()
	{
		this.addClassName("main-app-layout-base");
		this.setSpacing(false);
		Label textToAdd;
		
		submenuColors = new LinkedList<>();
		submenuColors.add("#fefecc");
		submenuColors.add("#feff99");
		submenuColors.add("#feff65");
		submenuColors.add("#feff32");
		submenuColors.add("#feff00");
		submenuColors.add("#cbcc00");
		
		menu = new MenuLayout();
		menu.setClassName("menu-layout-dynamic");
		
		content = new Div();
		content.setClassName("main-app-layout-content-with-menu");
		
		//
		VerticalLayout menuHeaderWrapper = new VerticalLayout();
		menuHeaderWrapper.setWidthFull();
		menuHeaderWrapper.setPadding(false);
		menuHeaderWrapper.setSpacing(false);
		menuHeaderWrapper.getStyle().set("margin-bottom", "20px");
		HorizontalLayout menuHeader = new HorizontalLayout();
		menuHeader.addClassName("header-item-style");
		
		TogglableActionIcon menuIcon = new TogglableActionIcon("icon-item-rotation-toggle-enabled",
				"icon-item-rotation-toggle-disabled", VaadinIcon.MENU.create(), () -> toggleMenuState());
		
		menuHeader.add(menuIcon);
		
		Div logoWrapper = new Div();
		VerticalLayout logo = new  VerticalLayout();
		logo.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		textToAdd = new Label("xPlorer");
		textToAdd.getStyle().set("font-size", "xx-large");
		textToAdd.getStyle().set("font-style", "bold");
		textToAdd.getStyle().set("padding-left", "4px");
		logo.add(textToAdd);
		textToAdd = new Label("ver. 3.0");
		textToAdd.getStyle().set("font-size", "xx-small");
		textToAdd.getStyle().set("padding-left", "4px");
		textToAdd.getStyle().set("margin-bottom", "20px");
		logo.add(textToAdd);
		logoWrapper.add(logo);
		FlexLayout wrapper = new FlexLayout(logoWrapper);
		
		wrapper.setJustifyContentMode(JustifyContentMode.CENTER);
		menuHeader.expand(wrapper);
		menuHeader.add(wrapper);
		menuHeader.setWidthFull();
		
		menuHeaderWrapper.add(menuHeader, SeparatorElement.create());

		
		
		VerticalLayout menuFooter = new VerticalLayout();
		menuFooter.setSizeFull();
		menuFooter.setPadding(false);
		menuFooter.setSpacing(false);
		
		menu.add(menuHeaderWrapper, menuFooter);
		
		add(menu, content);
		setFlexGrow(1, content);
		
		menuFooter.add(createSubMenu());
		menuFooter.add(createSubMenu());
		menuFooter.add(createSubMenu());
		menuFooter.add(createSubMenu());
		

		
		
		
		
		BackEnd backEnd = new BackEnd();
		
		Grid<Tester> grid = new Grid<>(Tester.class);
        grid.setSizeFull();
        
        ListDataProvider<Tester> ldp = new ListDataProvider<>(backEnd.getTesters());
        
        grid.setDataProvider(ldp);
        grid.addSectionScrolledListener(event -> 
        {
        	StringBuilder str = new StringBuilder();
        	
        	str.append("=============================");
        	str.append(System.lineSeparator());
        	
        	str.append("PercentageScrolled: ").append(event.getPercentageScrolled());
        	str.append(System.lineSeparator());
        	
        	str.append("=============================");
        	str.append(System.lineSeparator());
        	
        	System.out.println(str.toString());
        });
        grid.addScrollerReachedBottomAreaListener(event -> 
        {
        	StringBuilder str = new StringBuilder();
        	
        	str.append("=============================");
        	str.append(System.lineSeparator());
        	
        	str.append("Crossed 80% of Scrollbar");
        	str.append(System.lineSeparator());
        	
        	str.append("=============================");
        	str.append(System.lineSeparator());
        	
        	System.out.println(str.toString());
        });
        
        content.add(grid);
	}
	
	private void toggleMenuState()
	{
		if(menuDisplayType == MenuDisplayType.DYNAMIC)
		{
			menuDisplayType = MenuDisplayType.EXTENDED;
			menu.setClassName("menu-layout-extended");
			content.setClassName("main-app-layout-content");
		}
		else if(menuDisplayType == MenuDisplayType.EXTENDED)
		{
			menuDisplayType = MenuDisplayType.COLLAPSED;
			menu.setClassName("menu-layout-collapsed");
			content.setClassName("main-app-layout-content-with-menu");
		}
		else
		{
			menuDisplayType = MenuDisplayType.DYNAMIC;
			menu.setClassName("menu-layout-dynamic");
			content.setClassName("main-app-layout-content-with-menu");
		}
	}

	private VerticalLayout createSubMenu()
	{
		Label textToAdd;
		VerticalLayout submenu = new VerticalLayout();
		
		submenu.getStyle().set("padding", "0px 0px 15px 0px");
		
		HorizontalLayout button3 = new HorizontalLayout();
		button3.addClassName("menu-item-style");

		Icon icon = VaadinIcon.ALARM.create();
		textToAdd = new Label("Button3");
		textToAdd.getStyle().set("font-weight", "400");
		
		HorizontalLayout firstPart = new HorizontalLayout();
		firstPart.add(icon, textToAdd);
		button3.add(firstPart);
		
		Icon submenuDropdownIcon = VaadinIcon.CHEVRON_DOWN_SMALL.create();
		FlexLayout wrapper = new FlexLayout(submenuDropdownIcon);
		
		wrapper.addClassName("tester");
		button3.expand(wrapper);
		button3.add(wrapper);
		
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
        
		button3.setWidthFull();
		
		button3.addClickListener(e ->
		{
			collapsibleSubmenu.toggle();
		});
		
		submenu.add(button3, collapsibleSubmenu);
		
		return submenu;
	}
	
	public class BackEnd implements PageableService<Tester>
    {
    	private List<Tester> testers;
    	
    	
    	public BackEnd()
    	{
    		testers = new LinkedList<Tester>()
    				{{
    					for(int i=1;i<=2000;i++)
    						add(new Tester("1."+i,"2."+i));
    				}};
    	}
    	
    	public List<Tester> getTesters()
    	{
    		return testers;
    	}

		@Override
		public List<Tester> fetch(int offset, int limit)
		{
			return testers.subList(1, 31);
		}

		@Override
		public int getCount()
		{
			return 30;
		}
    }
    
    public interface PageableService<T>
    {
    	  List<T> fetch(int offset, int limit);
    	  int getCount();
    	}
    
    public class Tester
    {
    	private String fieldOne;
    	private String fieldTwo;
    	
    	public Tester(String fieldOne, String fieldTwo)
    	{
			setFieldOne(fieldOne);
			setFieldTwo(fieldTwo);
		}
		public String getFieldOne() {
			return fieldOne;
		}
		public void setFieldOne(String fieldOne) {
			this.fieldOne = fieldOne;
		}
		public String getFieldTwo() {
			return fieldTwo;
		}
		public void setFieldTwo(String fieldTwo) {
			this.fieldTwo = fieldTwo;
		}
    }
}

package com.packagename.myapp.spring;

import java.util.LinkedList;
import java.util.List;

import com.github.appreciated.IronCollapse;
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
	
	/**
	 * 
	 */
	
	public DefaultView()
	{
		this.addClassName("main-app-layout");
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
		content = new Div();
		content.addClassName("main-app-layout-content");
		
		//
		VerticalLayout menuHeaderWrapper = new VerticalLayout();
		menuHeaderWrapper.setWidthFull();
		menuHeaderWrapper.setPadding(false);
		menuHeaderWrapper.setSpacing(false);
		menuHeaderWrapper.getStyle().set("margin-bottom", "20px");
		HorizontalLayout menuHeader = new HorizontalLayout();
		menuHeader.addClassName("header-item-style");

		Icon icon = VaadinIcon.MENU.create();

		Div firstPart = new Div();
		firstPart.add(icon);
		menuHeader.add(firstPart);
		
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
		
		Div sep = new Div();
		sep.setWidthFull();
		sep.setHeight("2px");
		sep.getStyle().set("background-color", "black");
		
		menuHeaderWrapper.add(menuHeader, sep);

		
		
		VerticalLayout menuFooter = new VerticalLayout();
		menuFooter.setSizeFull();
		menuFooter.setPadding(false);
		menuFooter.setSpacing(false);
		
		menu.add(menuHeaderWrapper, menuFooter);
		menu.addClassName("moving");
		
		add(menu, content);
		setFlexGrow(1, content);
		
		menuFooter.add(createSubMenu());
		menuFooter.add(createSubMenu());
		menuFooter.add(createSubMenu());
		menuFooter.add(createSubMenu());
		
//		Icon submenuDropdownIcon = VaadinIcon.SIGN_OUT_ALT.create();
//		FlexLayout wrapper = new FlexLayout(submenuDropdownIcon);
//		wrapper.setJustifyContentMode(JustifyContentMode.CENTER);
		
//		wrapper.addClassName("tester");
//		menu.expand(menuFooter);
//		menu.add(wrapper);
		
		BackEnd backEnd = new BackEnd();
		
		Grid<Tester> grid = new Grid<>(Tester.class);
        grid.setSizeFull();
        
        ListDataProvider<Tester> ldp = new ListDataProvider<>(backEnd.getTesters());
        
//        DataProvider.fromcallbacks
        
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

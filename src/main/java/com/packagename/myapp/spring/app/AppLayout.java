package com.packagename.myapp.spring.app;

import java.util.LinkedList;
import java.util.List;

import com.packagename.myapp.spring.Grid;
import com.packagename.myapp.spring.menu.BodySection;
import com.packagename.myapp.spring.menu.CompositeWrapperHorizontalLayout;
import com.packagename.myapp.spring.menu.HeaderSection;
import com.packagename.myapp.spring.menu.Menu;
import com.packagename.myapp.spring.menu.Section;
import com.packagename.myapp.spring.menu.item.BodyMenuItem;
import com.packagename.myapp.spring.menu.item.HeaderHamburgerMenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route(value = "")
@CssImport(value = "./styles/SpecificStyles.css")
public class AppLayout extends CompositeWrapperHorizontalLayout
{
	private static final long serialVersionUID = 1366743745420863584L;
	
	public AppLayout()
	{
		Div content = new Div();
		Section headerSection = createHeaderSection();
		Section bodySection = createBodySection();
		
		Menu menuBar = Menu.create(List.of(headerSection, bodySection));
		
		content.setClassName("main-app-layout-content-with-menu");
		menuBar.setClassName("menu-layout-dynamic");
		
		contentWrapper.add(menuBar, content);
		contentWrapper.setFlexGrow(1, content);
		
		
		//------------------------------------------------------------
		
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
	
	//-------------------------------------------------------
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
	//-------------------------------------------------------

	private Section createBodySection()
	{
		Section bodySection = new BodySection();
		
		BodyMenuItem bodyMenuItem = new BodyMenuItem(VaadinIcon.ABACUS.create(), "First");
		bodyMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.AMBULANCE.create(), "First.First"));
		bodyMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.AIRPLANE.create(), "First.First"));
		
		bodySection.addItem(bodyMenuItem);
		
		return bodySection;
	}

	private Section createHeaderSection()
	{
		Section headerSection = new HeaderSection();
		
		headerSection.addItem(
				new HeaderHamburgerMenuItem()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Div createLogoComponent()
			{
				Div logoWrapper = new Div();
				VerticalLayout logo = new  VerticalLayout();
				logo.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
				Label textToAdd = new Label("xPlorer");
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
				
				return logoWrapper;
			}
		});
		
		return headerSection;
	}
}

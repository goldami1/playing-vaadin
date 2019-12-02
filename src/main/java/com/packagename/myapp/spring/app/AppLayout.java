package com.packagename.myapp.spring.app;

import java.util.Arrays;
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
import com.packagename.myapp.spring.menu.item.components.CustomTitleLabel;
import com.packagename.myapp.spring.menu.item.components.CustomTitleLabel.TitleType;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

@Route(value = "")
@CssImport(value = "./styles/app-layout-styles.css")
public class AppLayout extends CompositeWrapperHorizontalLayout
{
	private static final long serialVersionUID = 1366743745420863584L;
	private enum AppLayoutDisplayType {
		EXTENDED("menu-layout-extended", "main-app-layout-content"),
		COLLAPSED("menu-layout-collapsed", "main-app-layout-content-with-menu"),
		DYNAMIC("menu-layout-dynamic", "main-app-layout-content-with-menu");
		
		String menubarCssConfig, contentCssConfig;
		
		public String getContentCssConfig()
		{
			return contentCssConfig;
		}
		
		public String getMenubarCssConfig()
		{
			return menubarCssConfig;
		}
		
		private AppLayoutDisplayType(String menubarCssConfig, String contentCssConfig)
		{
			this.menubarCssConfig = menubarCssConfig;
			this.contentCssConfig = contentCssConfig;
		}
		
	};
	private AppLayoutDisplayType menuDisplayType = AppLayoutDisplayType.DYNAMIC;
	private Menu menuBar;
	private Div content;
	
	public AppLayout()
	{
		contentWrapper.addClassName("main-app-layout-base");
		content = new Div();
		Section headerSection = createHeaderSection();
		Section bodySection = createBodySection();
		
		menuBar = Menu.create(List.of(headerSection, bodySection));
		
		setAppLayoutClassNames(menuDisplayType);
		
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
		
		BodyMenuItem homeMenuItem = new BodyMenuItem(VaadinIcon.HOME.create(), "Home");
		BodyMenuItem topologyMenuItem = new BodyMenuItem(VaadinIcon.SITEMAP.create(), "Topology");
		BodyMenuItem remotes = new BodyMenuItem(VaadinIcon.CLOUD.create(), "Remotes");
		remotes.addSubItem(new BodyMenuItem(VaadinIcon.BULLSEYE.create(), "Bullet1"));
		remotes.addSubItem(new BodyMenuItem(VaadinIcon.BULLSEYE.create(), "Bullet2"));
		remotes.addSubItem(new BodyMenuItem(VaadinIcon.BULLSEYE.create(), "Bullet3"));
		topologyMenuItem.addSubItem(remotes);
		topologyMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.STORAGE.create(), "Storages"));
		topologyMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.CLUSTER.create(), "xTenders"));
		topologyMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.COMPILE.create(), "xRWRRecorders"));
		BodyMenuItem pluginsMenuItem = new BodyMenuItem(VaadinIcon.PLUG.create(), "Plugins");
		pluginsMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.FOLDER.create(), "Libraries"));
		pluginsMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.INBOX.create(), "Collectors"));
		pluginsMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.FLIP_V.create(), "Transformers"));
		pluginsMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.EYEDROPPER.create(), "Analysers"));
		pluginsMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.BELL_O.create(), "Notifiers"));
		BodyMenuItem configurationMenuItem = new BodyMenuItem(VaadinIcon.COGS.create(), "Configuration");
		configurationMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.INBOX.create(), "Collector Configurations"));
		configurationMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.FLIP_V.create(), "Transformer Configurations"));
		configurationMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.EYEDROPPER.create(), "Analyser Configurations"));
		configurationMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.FILTER.create(), "Filter Configurations"));
		configurationMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.VIEWPORT.create(), "View Configurations"));
		configurationMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.BELL_O.create(), "Notifier Configurations"));
		BodyMenuItem sessionsMenuItem = new BodyMenuItem(VaadinIcon.CHART_GRID.create(), "Sessions");
		sessionsMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.RECORDS.create(), "5d7f6bae1e4d990c68223918"));
		sessionsMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.RECORDS.create(), "5d7f6b1f1e4d990c68223902"));
		BodyMenuItem gridMenuItem = new BodyMenuItem(VaadinIcon.GRID_SMALL_O.create(), "Grid");
		gridMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.CONNECT_O.create(), "Nodes"));
		gridMenuItem.addSubItem(new BodyMenuItem(VaadinIcon.CLIPBOARD_CHECK.create(), "Execute Results"));
		BodyMenuItem supportMenuItem = new BodyMenuItem(VaadinIcon.SPECIALIST.create(), "Support");
		
		bodySection.addItem(homeMenuItem);
		bodySection.addItem(topologyMenuItem);
		bodySection.addItem(pluginsMenuItem);
		bodySection.addItem(configurationMenuItem);
		bodySection.addItem(sessionsMenuItem);
		bodySection.addItem(gridMenuItem);
		bodySection.addItem(supportMenuItem);
		
		return bodySection;
	}

	private Section createHeaderSection()
	{
		Section headerSection = new HeaderSection();
		HeaderHamburgerMenuItem hamburgerMenuItem = new HeaderHamburgerMenuItem()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Div createLogoComponent()
			{
				Div logoWrapper = new Div();
				logoWrapper.getStyle().set("margin-left", "2em");
				VerticalLayout logo = new  VerticalLayout();
				logo.setPadding(false);
				logo.setMargin(false);
				logo.setSpacing(false);
				logo.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
				CustomTitleLabel xplorerHeader = new CustomTitleLabel("xPlorer", TitleType.TITLE);
				CustomTitleLabel xplorerVersion = new CustomTitleLabel("3.0", TitleType.SUBTITLE);
				logo.add(xplorerHeader, xplorerVersion);
				logoWrapper.add(logo);
				
				return logoWrapper;
			}
		};
		
		hamburgerMenuItem.addMenuOnClickListener(e -> toggleMenuState());
		headerSection.addItem(hamburgerMenuItem);
		
		return headerSection;
	}
	
	private void toggleMenuState()
	{
		switch(menuDisplayType)
		{
		case DYNAMIC:
			menuDisplayType = AppLayoutDisplayType.EXTENDED;
			break;
		case EXTENDED:
			menuDisplayType = AppLayoutDisplayType.COLLAPSED;
			break;
		case COLLAPSED:
			menuDisplayType = AppLayoutDisplayType.DYNAMIC;
			break;
		}
		
		setAppLayoutClassNames(menuDisplayType);
	}
	
	private void setAppLayoutClassNames(AppLayoutDisplayType appLayoutDisplayType)
	{
		content.setClassName(appLayoutDisplayType.getContentCssConfig());
		Arrays.asList(AppLayoutDisplayType.values())
				.stream()
				.map(t -> t.getMenubarCssConfig())
				.filter(t -> !t.contentEquals(appLayoutDisplayType.getMenubarCssConfig()))
				.forEach(t -> menuBar.removeClassName(t));
		menuBar.addClassName(appLayoutDisplayType.getMenubarCssConfig());
	}
}

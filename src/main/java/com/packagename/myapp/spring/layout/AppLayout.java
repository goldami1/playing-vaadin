package com.packagename.myapp.spring.layout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.packagename.myapp.spring.Grid;
import com.packagename.myapp.spring.menu.BodySection;
import com.packagename.myapp.spring.menu.HeaderSection;
import com.packagename.myapp.spring.menu.Menu;
import com.packagename.myapp.spring.menu.Section;
import com.packagename.myapp.spring.menu.item.BodyMenuItem;
import com.packagename.myapp.spring.menu.item.HeaderHamburgerMenuItem;
import com.packagename.myapp.spring.menu.item.component.CustomTitleLabel;
import com.packagename.myapp.spring.menu.item.component.CustomTitleLabel.TitleType;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
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
	private enum MENU_DESCRIPTOR{
    	HOME("Home", VaadinIcon.HOME),
    	TOPOLOGY("Topology", VaadinIcon.SITEMAP),
    	REMOTES("Remotes", VaadinIcon.CLOUD),
    	STORAGES("Storages", VaadinIcon.STORAGE),
		XTENDERS("xTenders", VaadinIcon.CLUSTER),
		XRWRRECORDERS("xRWRRecorders", VaadinIcon.COMPILE),
		PLUGINS("Plugins", VaadinIcon.PLUG),
		LIBRARIES("Libraries", VaadinIcon.FOLDER),
		COLLECTORS("Collectors", VaadinIcon.INBOX),
		TRANSFORMERS("Transformers", VaadinIcon.FLIP_V),
		ANALYSERS("Analysers", VaadinIcon.EYEDROPPER),
		NOTIFIERS("Notifiers", VaadinIcon.BELL_O),
		CONFIGURATION("Configuration", VaadinIcon.COGS),
		COLLECTOR_CONFIGURATIONS("Collector Configurations", VaadinIcon.INBOX),
		TRANSFORMER_CONFIGURATIONS("Transformer Configurations", VaadinIcon.FLIP_V),
		ANALYSER_CONFIGURATIONS("Analyser Configurations", VaadinIcon.EYEDROPPER),
		FILTER_CONFIGURATIONS("Filter Configurations", VaadinIcon.FILTER),
		VIEW_CONFIGURATIONS("View Configurations", VaadinIcon.VIEWPORT),
		NOTIFIER_CONFIGURATIONS("Notifier Configurations", VaadinIcon.BELL_O),
		SESSIONS("Sessions", VaadinIcon.CHART_GRID),
		SESSION_RECORD("SessionRecord", VaadinIcon.RECORDS),
		GRID("Grid", VaadinIcon.GRID_SMALL_O),
		NODES("Nodes", VaadinIcon.CONNECT_O),
		EXECUTE_RESULTS("Execute Results", VaadinIcon.CLIPBOARD_CHECK),
		SUPPORT("Support", VaadinIcon.SPECIALIST);
    	
    	private String name;
    	private VaadinIcon menuItemIcon;
    	
    	private MENU_DESCRIPTOR(String name, VaadinIcon menuItemIcon)
    	{
    		this.name = name;
    		this.menuItemIcon = menuItemIcon;
    	}
    	
		@SuppressWarnings("unused")
		public Icon getIcon()
    	{
    		return menuItemIcon.create();
    	}
    	
    	@Override
    	public String toString()
    	{
    		return name;
    	}
	};
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
	private Map<MENU_DESCRIPTOR, BodyMenuItem> menuItemsMap;
	private Menu menuBar;
	private Div content;
	
	public AppLayout()
	{
		menuItemsMap = new HashMap<>();
		
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
	
    private BodyMenuItem createBodyMenuItem(MENU_DESCRIPTOR menuDesc)
    {
    	BodyMenuItem res = new BodyMenuItem(menuDesc.getIcon(), menuDesc.toString());
    	menuItemsMap.put(menuDesc, res);
    	
    	return res;
    }
    
    private List<BodyMenuItem> createPluginsSubItems()
    {
    	List<BodyMenuItem> res = new LinkedList<>();
    	
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.LIBRARIES));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.COLLECTORS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.TRANSFORMERS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.ANALYSERS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.NOTIFIERS));
    	
    	return res;
    }
    
    private List<BodyMenuItem> createTopologySubItems()
    {
    	List<BodyMenuItem> res = new LinkedList<>();
    	
    	BodyMenuItem remotes = createBodyMenuItem(MENU_DESCRIPTOR.REMOTES);
		remotes.addSubItem(new BodyMenuItem(VaadinIcon.BULLSEYE.create(), "Bullet1"));
		remotes.addSubItem(new BodyMenuItem(VaadinIcon.BULLSEYE.create(), "Bullet2"));
		remotes.addSubItem(new BodyMenuItem(VaadinIcon.BULLSEYE.create(), "Bullet3"));
		
		res.add(remotes);
		res.add(createBodyMenuItem(MENU_DESCRIPTOR.STORAGES));
		res.add(createBodyMenuItem(MENU_DESCRIPTOR.XTENDERS));
		res.add(createBodyMenuItem(MENU_DESCRIPTOR.XRWRRECORDERS));
		
		return res;
    }
    
    private List<BodyMenuItem> createConfigurationSubItems()
    {
    	List<BodyMenuItem> res = new LinkedList<>();
    	
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.COLLECTOR_CONFIGURATIONS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.TRANSFORMER_CONFIGURATIONS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.ANALYSER_CONFIGURATIONS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.FILTER_CONFIGURATIONS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.VIEW_CONFIGURATIONS));
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.NOTIFIER_CONFIGURATIONS));
    	
    	return res;
    }
    
    private List<BodyMenuItem> createGridSubItems()
    {
    	List<BodyMenuItem> res = new LinkedList<>();
    	
    	res.add(createBodyMenuItem(MENU_DESCRIPTOR.NODES));
		res.add(createBodyMenuItem(MENU_DESCRIPTOR.EXECUTE_RESULTS));
		
		return res;
    }
    
	private Section createBodySection()
	{
		Section bodySection = new BodySection();
		
		BodyMenuItem homeMenuItem = createBodyMenuItem(MENU_DESCRIPTOR.HOME);
		BodyMenuItem topologyMenuItem = createBodyMenuItem(MENU_DESCRIPTOR.TOPOLOGY);
		createTopologySubItems().forEach(item -> topologyMenuItem.addSubItem(item));
		BodyMenuItem pluginsMenuItem = createBodyMenuItem(MENU_DESCRIPTOR.PLUGINS);
		createPluginsSubItems().forEach(item -> pluginsMenuItem.addSubItem(item));
		BodyMenuItem configurationMenuItem = createBodyMenuItem(MENU_DESCRIPTOR.CONFIGURATION);
		createConfigurationSubItems().forEach(item -> configurationMenuItem.addSubItem(item));
		BodyMenuItem sessionsMenuItem = createBodyMenuItem(MENU_DESCRIPTOR.SESSIONS);
		sessionsMenuItem.addSubItem(new BodyMenuItem(MENU_DESCRIPTOR.SESSION_RECORD.getIcon(), "5d7f6bae1e4d990c68223918"));
		sessionsMenuItem.addSubItem(new BodyMenuItem(MENU_DESCRIPTOR.SESSION_RECORD.getIcon(), "5d7f6b1f1e4d990c68223902"));
		BodyMenuItem gridMenuItem = createBodyMenuItem(MENU_DESCRIPTOR.GRID);
		createGridSubItems().forEach(item -> gridMenuItem.addSubItem(item));
		BodyMenuItem supportMenuItem = createBodyMenuItem(MENU_DESCRIPTOR.SUPPORT);
		
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

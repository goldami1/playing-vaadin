package com.packagename.myapp.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;

public class MainView extends VerticalLayout
{
	private BackEnd backEnd = new BackEnd();
	
    public MainView(@Autowired MessageBean bean)
    {
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        
        Grid<Tester> grid = new Grid<>(Tester.class);
        grid.setWidth("100%");
        setSizeFull();
        setFlexGrow(1, button);
        setFlexGrow(15, grid);
        
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
        
        add(button, grid);
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

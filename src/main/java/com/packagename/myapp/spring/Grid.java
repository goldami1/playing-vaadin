package com.packagename.myapp.spring;

import java.util.Objects;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.shared.Registration;

@SuppressWarnings("serial")
public class Grid<T> extends com.vaadin.flow.component.grid.Grid<T>
{
	public Grid(Class<T> beanType)
	{
        super(beanType);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Registration addSectionScrolledListener(
            ComponentEventListener<GridSectionScrolledEvent<T>> listener) {
        return addListener(GridSectionScrolledEvent.class,
                (ComponentEventListener) Objects.requireNonNull(listener));
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Registration addScrollerReachedBottomAreaListener(
            ComponentEventListener<GridScrollerReachedBottomAreaEvent<T>> listener) {
        return addListener(GridScrollerReachedBottomAreaEvent.class,
                (ComponentEventListener) Objects.requireNonNull(listener));
    }
}

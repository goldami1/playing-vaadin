package com.packagename.myapp.spring;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@SuppressWarnings("serial")
@DomEvent("scroller-reached-table-bottom-area")
public class GridScrollerReachedBottomAreaEvent<T> extends ComponentEvent<Grid<T>>
{
    public GridScrollerReachedBottomAreaEvent(Grid<T> source, boolean fromClient)
    {
        super(source, fromClient);
    }
}

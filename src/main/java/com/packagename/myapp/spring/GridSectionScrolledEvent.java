package com.packagename.myapp.spring;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.grid.Grid;

import elemental.json.JsonObject;

@SuppressWarnings("serial")
@DomEvent("scrolled-percentage-changed")
public class GridSectionScrolledEvent<T> extends ComponentEvent<Grid<T>>
{
    private final double percentageScrolled;
    
    public GridSectionScrolledEvent(Grid<T> source, boolean fromClient,
            @EventData("event.detail") JsonObject details)
    {
        super(source, fromClient);
        percentageScrolled = details.get("percentageScrolled").asNumber();
    }

	public double getPercentageScrolled() {
		return percentageScrolled;
	}
}

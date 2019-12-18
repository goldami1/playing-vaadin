package com.packagename.myapp.spring.menu.item.component;

import com.github.goldami1.vaadin.tooltip.POSITION;
import com.github.goldami1.vaadin.tooltip.component.TooltipComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;

public interface HasTooltip extends HasElement
{
	default TooltipComponent getTooltipWrapped()
	{
//		this.getElement().setProperty("title", description);
//		getTooltipElement().getElement().setProperty("title", description);
		TooltipComponent res = TooltipComponent.setDescription(getTooltipElement(), getTooltipText(), false);
		res.getTooltip().getTooltipFacade().setPosition(POSITION.RIGHT);
		
		return res;
	}
	
	Component getTooltipElement();
	
	String getTooltipText();
}

package com.packagename.myapp.spring;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CenterHeaderItem extends Composite<VerticalLayout>
{
	 public CenterHeaderItem(String title, String subtitle, String src)
	 {
	        VerticalLayout content = getContent();
	        content.setPadding(false);
	        content.setAlignItems(Alignment.CENTER);
	        content.getStyle().set("padding","var(--app-layout-menu-header-padding)");
	        content.setMargin(false);
	        setId("menu-header-wrapper");
	        if (src != null) {
	        }
	        if (title != null) {
	            Label titleLabel = new Label(title);
	            titleLabel.setId("menu-header-title");
	            content.add(titleLabel);
	        }
	        if (subtitle != null) {
	            Label subtitleLabel = new Label(subtitle);
	            subtitleLabel.setId("menu-header-subtitle");
	            content.add(subtitleLabel);
	        }
	 }
}

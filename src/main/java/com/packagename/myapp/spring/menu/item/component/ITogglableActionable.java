package com.packagename.myapp.spring.menu.item.component;

public interface ITogglableActionable
{
	Runnable getToggleAction();
	default void activateOnToggle()
	{
		getToggleAction().run();
	}
}

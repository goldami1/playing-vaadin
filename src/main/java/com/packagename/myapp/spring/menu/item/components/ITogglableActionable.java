package com.packagename.myapp.spring.menu.item.components;

public interface ITogglableActionable
{
	Runnable getToggleAction();
	default void activateOnToggle()
	{
		getToggleAction().run();
	}
}

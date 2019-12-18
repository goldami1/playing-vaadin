package com.packagename.myapp.spring.menu;

import java.util.Collection;

public interface HasItems<T>
{
	Collection<T> getItems();
}

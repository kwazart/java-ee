package com.kwazart;

import org.springframework.stereotype.Component;

//@Component
public class ColorRoll implements CameraRoll{
	@Override
	public void processing() {
		System.out.println("-1 colored frame");
	}
}

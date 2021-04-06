package com.kwazart.simple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Camera camera = context.getBean("camera", Camera.class);
		camera.doPhoto();

		CameraRGB cameraRGB = context.getBean("cameraRGB", CameraRGB.class);
		cameraRGB.doPhoto();


		((AnnotationConfigApplicationContext) context).close();
	}
}

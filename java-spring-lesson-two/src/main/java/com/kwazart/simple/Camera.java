package com.kwazart.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Camera {

	private CameraRoll cameraRollRGB;
	private CameraRoll cameraRollBW;

	@Autowired
	@Qualifier("blackAndWhiteRoll")
	public void setCameraRollBW(CameraRoll cameraRoll) {
		this.cameraRollBW = cameraRoll;
	}

	@Autowired
	@Qualifier("colorRoll")
	public void setCameraRollRGB(CameraRoll cameraRoll) {
		this.cameraRollRGB = cameraRoll;
	}

	public void doPhoto() {
		System.out.println("Click!");
		cameraRollRGB.processing();
	}
}

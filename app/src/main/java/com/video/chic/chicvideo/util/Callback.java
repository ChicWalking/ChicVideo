package com.video.chic.chicvideo.util;

public interface Callback {
	void onBefore();

	boolean onRun();

	void onAfter(boolean b);
}

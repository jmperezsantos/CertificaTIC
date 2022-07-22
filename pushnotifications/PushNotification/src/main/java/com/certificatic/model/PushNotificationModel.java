package com.certificatic.model;

public class PushNotificationModel {

	/**
	 * Título de la push notification
	 */
	private String title;

	/**
	 * Cuerpo de la push notification
	 */
	private String body;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public PushNotificationModel(String title, String body) {

		super();

		this.title = title;
		this.body = body;
	}

	public PushNotificationModel() {
		super();
	}

	@Override
	public String toString() {
		return "PushNotificationModel [title=" + title + ", body=" + body + "]";
	}

}

package com.certificatic.model;

import java.util.HashMap;
import java.util.Map;

import com.certificatic.util.Constants;

public class PushNotificationMessageModel {

	/**
	 * Token del dispositivo al que le será enviada la notificación
	 */
	private String token;

	/**
	 * Estructura que será mostrada al llegar la notificación push al móvil
	 */
	private PushNotificationModel notification;

	/**
	 * Datos arbitrarios (extras) que se puenden enviar en la notificación
	 */
	private Map<String, String> data;

	public PushNotificationMessageModel(String token, 
			PushNotificationModel notification, 
			Map<String, String> data) {

		this.token = token;
		this.notification = notification;
		this.data = data;

		if (this.data == null) {
			this.data = new HashMap<>();
		}

		this.data.put(Constants.NOTIFICATION_TITLE_NAME, notification.getTitle());
		this.data.put(Constants.NOTIFICATION_BODY_NAME, notification.getBody());

	}

	public PushNotificationMessageModel() {

	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public PushNotificationModel getNotification() {
		return notification;
	}

	public void setNotification(PushNotificationModel notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return String.format("PushNotificationMessageModel [token=%s, notification=%s, data=%s]", token, notification,
				data);
	}

}

package com.certificatic.model;

public class PushNotificationPayload {

	/**
	 * Mensaje que será enviado al móvil
	 */
	private PushNotificationMessageModel message;

	public PushNotificationPayload() {
	}

	public PushNotificationPayload(PushNotificationMessageModel message) {

		this.message = message;

	}

	public void setMessage(PushNotificationMessageModel message) {
		this.message = message;
	}

	public PushNotificationMessageModel getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "PushNotificationPayload [message=" + message + "]";
	}

}

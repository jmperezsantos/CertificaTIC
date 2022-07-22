package com.certificatic.service;

import com.certificatic.model.PushNotificationPayload;

/**
 * Interfaz para manejo de envío de push notification
 * 
 * @author Manuel Pérez
 *
 */
public interface IGooglePushNotificationService {

	/**
	 * Método para envío de push notifications
	 * 
	 * @param payload
	 *            El contenido de la notificación a enviar
	 */
	public void sendNotification(PushNotificationPayload payload);

	/**
	 * Método para envío de push notifications genéricas
	 * 
	 * @param payload
	 *            El contenido de la notificación a enviar
	 */
	public void sendGenericNotification(PushNotificationPayload payload);

}

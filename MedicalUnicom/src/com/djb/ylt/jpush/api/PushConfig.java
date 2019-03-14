package com.djb.ylt.jpush.api;

import java.util.Map;

import cn.jpush.api.push.model.notification.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.push.model.Options;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;

import cn.jpush.api.push.model.audience.Audience;

public class PushConfig {
	protected static final Logger LOG = LoggerFactory.getLogger(PushConfig.class);

	// demo App defined in resources/jpush-api.conf
	private static final String appKey = "1d1072f2c470b4dcb2e641f4";
	private static final String masterSecret = "2d52e66cff7dd1fbcd5f247d";
	public static final String TITLE = "温馨提示：您有新的视频预约，请及时查看";
	public static final String TITLE_PATIENT = "温馨提示：医生已经填写问诊结果，请及时查看";
	public static final String ALERT = "温馨提示：您有新的视频预约，请及时查看";
	public static final String ALERT_PATIENT = "温馨提示：医生已经填写问诊结果，请及时查看";
	public static final String MSG_CONTENT = "Test from API Example - msgContent";
	public static final String REGISTRATION_ID = "0900e8d85ef";
	public static final String TAG = "tag_api";
	public static final String ALERT_CANCEL = "温馨提示：您的一个视频问诊被取消，请及时查看";
	public static final String TITLE_CANCEL = "温馨提示：您的一个视频问诊被取消，请及时查看";

	public static void main(String[] args) {

	}

	public static PushPayload buildPushObject_android_and_ios(String alias, Map<String, String> extras) {
		// Map<String, String> extras = new HashMap<String, String>();
		// extras.put("patientId", "1");
		// extras.put("extra_2", "val2");
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias))

				.setNotification(Notification.newBuilder().setAlert(ALERT)
						.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).build())
						.addPlatformNotification(IosNotification.newBuilder().setSound("sound.caf").incrBadge(1)
								.addExtras(extras).build())
						.build())
				.setOptions(Options.newBuilder()
						// 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
						.setApnsProduction(false)
						// 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
						.setSendno(1)
						// 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
						.setTimeToLive(86400).build())
				.build();
	}

	public static PushPayload buildPushObjectForPatient(String alias, Map<String, String> extras) {

		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias))
				.setNotification(
						Notification.newBuilder().setAlert(ALERT_PATIENT)
								.addPlatformNotification(
										AndroidNotification.newBuilder().setTitle(TITLE_PATIENT).build())
								.addPlatformNotification(IosNotification.newBuilder().setSound("sound.caf").incrBadge(1)
										.addExtras(extras).build())
								.build())
				.build();
	}

	public static PushPayload buildPushObjectForAll(String alias, Map<String, String> extras, String alert,
			String title) {
		// Map<String, String> extras = new HashMap<String, String>();
		// extras.put("patientId", "1");
		// extras.put("extra_2", "val2");
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder().setAlert(alert)
						.addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).build())
						.addPlatformNotification(IosNotification.newBuilder().setSound("sound.caf").incrBadge(1)
								.addExtras(extras).build())
						.build())
				.build();
	}

	public static void sendNotificationWithAlias(String alias, Map<String, String> extras) {

		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		PushPayload payload = buildPushObject_android_and_ios(alias, extras);
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}

	public static void sendNotification(String alias, Map<String, String> extras, String alert, String title) {

		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		PushPayload payload = buildPushObjectForAll(alias, extras, alert, title);
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}

	public static void sendNotificationWithAliasForPatient(String alias, Map<String, String> extras) {
		// ClientConfig config = ClientConfig.getInstance();
		// Setup the custom hostname
		// config.setPushHostName("https://api.jpush.cn");

		// JPushClient jpushClient = new JPushClient(masterSecret, appKey, null,
		// config);
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);

		PushPayload payload = buildPushObjectForPatient(alias, extras);
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}

}

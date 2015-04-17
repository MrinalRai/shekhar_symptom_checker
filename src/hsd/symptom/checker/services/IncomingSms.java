package hsd.symptom.checker.services;

import hsd.symptom.checker.CheckSMSAutoActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class IncomingSms extends BroadcastReceiver {

	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();

	public void onReceive(Context context, Intent intent) {

		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		try {

			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage
							.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage
							.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					String message = currentMessage.getDisplayMessageBody();

					Log.e("SmsReceiver", "senderNum: " + senderNum
							+ "; message: " + message);

					if (senderNum.contains("DM-HSCCLN")) {

						Log.e("SmsReceiver", "senderNum: " + senderNum);
						message = message.replace("Your OTP is:", "");
						Log.e("OTP", "OTP: " + message.trim());

						try {
							CheckSMSAutoActivity.sendOTPVerification(message);
						} catch (Exception e) {
						}
					}

					// Show Alert
					// int duration = Toast.LENGTH_LONG;
					// if (MainActivity.OTP.equals("OTP")) {
					// Toast toast = Toast
					// .makeText(context, "senderNum: " + senderNum
					// + ", message: " + message, duration);
					// toast.show();
					// }

				} // end for loop
			} // bundle is null

		} catch (Exception e) {
			Log.e("SmsReceiver", "Exception smsReceiver" + e);

		}
	}
}
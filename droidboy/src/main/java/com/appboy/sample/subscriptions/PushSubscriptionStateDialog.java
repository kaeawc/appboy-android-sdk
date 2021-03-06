package com.appboy.sample.subscriptions;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.appboy.Appboy;
import com.appboy.enums.NotificationSubscriptionType;
import com.appboy.support.AppboyLogger;

public class PushSubscriptionStateDialog extends SubscriptionStateDialogBase {
  private static final String TAG = AppboyLogger.getBrazeLogTag(PushSubscriptionStateDialog.class);

  public PushSubscriptionStateDialog(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public PushSubscriptionStateDialog(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public void onDialogClosed(boolean positiveResult) {
    if (positiveResult) {
      int subscriptionStateId = mSubscriptionState.getCheckedRadioButtonId();
      View subscriptionStateRadioButton = mSubscriptionState.findViewById(subscriptionStateId);
      int subscriptionState = mSubscriptionState.indexOfChild(subscriptionStateRadioButton);
      switch (subscriptionState) {
        case SUBSCRIBED_INDEX:
          Appboy.getInstance(getContext()).getCurrentUser().setPushNotificationSubscriptionType(NotificationSubscriptionType.SUBSCRIBED);
          Toast.makeText(getContext(), "Set push subscription state to subscribed.", Toast.LENGTH_SHORT).show();
          break;
        case OPTED_IN_INDEX:
          Appboy.getInstance(getContext()).getCurrentUser().setPushNotificationSubscriptionType(NotificationSubscriptionType.OPTED_IN);
          Toast.makeText(getContext(), "Set push subscription state to opted-in.", Toast.LENGTH_SHORT).show();
          break;
        case UNSUBSCRIBED_INDEX:
          Appboy.getInstance(getContext()).getCurrentUser().setPushNotificationSubscriptionType(NotificationSubscriptionType.UNSUBSCRIBED);
          Toast.makeText(getContext(), "Set push subscription state to unsubscribed.", Toast.LENGTH_SHORT).show();
          break;
        default:
          Log.w(TAG, "Error parsing subscription state: " + subscriptionState);
          Toast.makeText(getContext(), "Error parsing subscription state: " + subscriptionState, Toast.LENGTH_SHORT).show();
      }
    }
  }
}

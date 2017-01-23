package com.grounduphq.arrispwgen;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import org.threeten.bp.LocalDate;

public class ArrispwgenWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            String seed = "MSKGJEUSHG";
            String potd = Arrispwgen.generate(LocalDate.now(), seed);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.potd_widget);
            remoteViews.setTextViewText(R.id.textView, potd);

            Intent intent = new Intent(context, ArrispwgenWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
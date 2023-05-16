package com.example.calendarofyourlife


import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmNotification:BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context, p1: Intent?) {
        createSimpleNotification(context)
    }
    @SuppressLint("MissingPermission")
    private fun createSimpleNotification(context: Context) {
        val intent = Intent(context, Notifications::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val notification = NotificationCompat.Builder(context, Notifications.MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle("SEMANA/OBJETIVO COMPLETADO")
            .setContentText("Marca tu semana/objetivo como completado")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Marca tu semana/objetivo como completado en el calendario de la vida")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)
    }

}
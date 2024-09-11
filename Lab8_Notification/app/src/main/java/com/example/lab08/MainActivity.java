package com.example.lab08;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button sendnotification;
    private static final int REQUEST_CODE_POST_NOTIFICATIONS = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        sendnotification = findViewById(R.id.send_notification);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{ android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
            }
        }

        sendnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPostNotificationsPermission()) {
                    sendNotification();
                } else {
                    requestPostNotificationsPermission();
                }
            }
        });
    }

    private boolean checkPostNotificationsPermission() {
        return ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
    }

//    private void requestPostNotificationsPermission() {
//        if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
//            // Explain why the permission is needed (optional)
//            Toast.makeText(this, "This app needs notification permission to send alerts.", Toast.LENGTH_SHORT).show();
//        }
//
//        ActivityCompat.requestPermissions(this,
//                new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
//    }

    private void requestPostNotificationsPermission() {
        if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
            // Explanation using AlertDialog for wider compatibility
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notification Permission Needed");
            builder.setMessage("This app needs notification permission to send alerts. Granting permission allows you to receive important updates from the app.");
            builder.setPositiveButton("Grant Permission", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
                }
            });
            builder.setNegativeButton("Deny", null);
            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_POST_NOTIFICATIONS);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_POST_NOTIFICATIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send notification
                sendNotification();
            } else {
                // Permission denied, inform user (optional)
                Toast.makeText(this, "Notification permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendNotification() {
        String CHANNEL_ID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("Gui thong bao")
                .setContentText("thong bao duoc gui tu app")
                .setColor(getResources().getColor(R.color.colorAccent))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    mNotificationManager.getNotificationChannel(CHANNEL_ID);
            if (notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel =
                        new NotificationChannel(CHANNEL_ID, "CHANNEL_NAME", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                mNotificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if (mNotificationManager != null){
            mNotificationManager.notify(getNotification(), builder.build());
        }

    }

    private int getNotification(){
        return (int) new Date().getTime();
    }

}
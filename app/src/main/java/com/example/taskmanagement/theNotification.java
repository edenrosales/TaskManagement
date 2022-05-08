package com.example.taskmanagement;


//Pops up when:
//1) Task is started/added
//2) Task is ended/deleted
//3) enable/disable notifs for certain tasks


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class theNotification extends AppCompatActivity {

    private Button notifWhenTaskSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        /*notifWhenTaskSubmit = (Button) findViewById(R.id.button2);


        //Gets triggered when a new Task is added
        notifWhenTaskSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When task added, Notification pops up....
                NotificationCompat.Builder builder = new NotificationCompat.Builder(theNotification.this, "addNotif111");
                builder.setContentTitle("Testing Title");
                builder.setContentText("This see");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(theNotification.this);
                managerCompat.notify(1,builder.build());

                //Log.d("HII","YE9YEIWE");*/

           // }
        //});
    }



}

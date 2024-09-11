package com.example.mergelab234;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends Activity {

    TextView txtReceived;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnBack = findViewById(R.id.btnMain);
        txtReceived = findViewById(R.id.textView);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, Lab4ClickShowActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (intent != null) {
            if (intent.hasExtra("STRING_KEY")) {
                String stringValue = intent.getStringExtra("STRING_KEY");
                txtReceived.setText("String Data: " + stringValue);
            } else if (intent.hasExtra("NUMBER_KEY")) {
                int numberValue = intent.getIntExtra("NUMBER_KEY", 0);
                txtReceived.setText("Number Data: " + numberValue);
            } else if (intent.hasExtra("OBJECT_KEY")) {
                StudentObject myObject = (StudentObject) intent.getSerializableExtra("OBJECT_KEY");
                String objectText = "Object Data\n" +
                        "Name: " + myObject.getName() + "\n" +
                        "Age: " + myObject.getAge() + "\n" +
                        "Class: " + myObject.getClassroom();
                txtReceived.setText(objectText);
            } else if (intent.hasExtra("ARRAY_KEY")) {
                ArrayList<String> stringArrayList = intent.getStringArrayListExtra("ARRAY_KEY");
                StringBuilder arrayText = new StringBuilder("Array Data: ");
                for (int i = 0; i < stringArrayList.size(); i++) {
                    arrayText.append(stringArrayList.get(i));
                    if (i < stringArrayList.size() - 1) {
                        arrayText.append(", ");
                    }
                }
                txtReceived.setText(arrayText.toString().trim());
            } else if (bundle != null && bundle.containsKey("BUNDLE_STRING_KEY") && bundle.containsKey("BUNDLE_DOUBLE_KEY") &&
                    bundle.containsKey("BUNDLE_INT_KEY") && bundle.containsKey("BUNDLE_BOOLEAN_KEY") && bundle.containsKey("BUNDLE_STRING_ARRAY_KEY")) {
                String bundleString = bundle.getString("BUNDLE_STRING_KEY");
                double bundleDouble = bundle.getDouble("BUNDLE_DOUBLE_KEY", 0);
                int bundleInt = bundle.getInt("BUNDLE_INT_KEY", 0);
                boolean bundleBoolean = bundle.getBoolean("BUNDLE_BOOLEAN_KEY", false);
                String[] bundleStringArray = bundle.getStringArray("BUNDLE_STRING_ARRAY_KEY");

                StringBuilder bundleText = new StringBuilder("Bundle Data\n");
                bundleText.append("String: ").append(bundleString).append("\n");
                bundleText.append("Double: ").append(bundleDouble).append("\n");
                bundleText.append("Int: ").append(bundleInt).append("\n");
                bundleText.append("Boolean: ").append(bundleBoolean).append("\n");
                bundleText.append("String Array: ");
                for (int i = 0; i < bundleStringArray.length; i++) {
                    bundleText.append(bundleStringArray[i]);
                    if (i < bundleStringArray.length - 1) {
                        bundleText.append(", ");
                    }
                }

                txtReceived.setText(bundleText.toString().trim());
            }
        }
    }
}

package org.ros.android.android_leak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.ros.android.RosActivity;
import org.ros.node.NodeMainExecutor;

public class MainActivity extends RosActivity {

    public MainActivity() {
        super("Leak", "Leak");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        //Do Nothing Here
    }
}

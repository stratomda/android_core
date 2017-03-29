package org.ros.android.android_leak;

import android.app.Activity;
import android.os.Bundle;

import org.ros.address.InetAddressFactory;
import org.ros.android.NodeMainExecutorService;
import org.ros.android.RosActivity;
import org.ros.node.Node;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

public class SecondActivity extends RosActivity {

    private NodeMainExecutor mNodeMainExecutor;

    private Heartbeat mHeartbeat;


    public SecondActivity() {
        super("Leak", "Leak");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mHeartbeat = new Heartbeat();
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        mNodeMainExecutor = nodeMainExecutor;
        NodeConfiguration nodeConfiguration = NodeConfiguration.
                newPublic(InetAddressFactory.newNonLoopback().getHostAddress(),getMasterUri());

        if(mNodeMainExecutor != null)
            mNodeMainExecutor.execute(mHeartbeat,nodeConfiguration);

    }

    @Override
    protected void onDestroy() {

        if(mNodeMainExecutor != null)
            if(mHeartbeat != null)
                mNodeMainExecutor.shutdownNodeMain(mHeartbeat);

        super.onDestroy();
    }
}

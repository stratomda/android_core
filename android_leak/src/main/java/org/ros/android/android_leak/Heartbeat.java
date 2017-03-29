package org.ros.android.android_leak;

import android.util.Log;

import org.ros.concurrent.CancellableLoop;
import org.ros.message.MessageListener;
import org.ros.namespace.GraphName;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;
import org.ros.node.topic.Subscriber;
import org.ros.node.topic.SubscriberListener;

import java.util.concurrent.TimeUnit;

import std_msgs.Header;
import std_msgs.String;

/**
 * Created by dambrosio on 3/27/17.
 */

public class Heartbeat implements NodeMain {

    public Heartbeat() {
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("heartbeat");
    }

    @Override
    public void onStart(ConnectedNode connectedNode) {
        final Publisher<Header> publisher = connectedNode.newPublisher("heartbeat", Header._TYPE);

        connectedNode.executeCancellableLoop(new CancellableLoop() {

            private int sequenceNumber;
            private std_msgs.Header msg;

            @Override
            protected void setup() {
                sequenceNumber = 0;
                msg = publisher.newMessage();
                msg.setFrameId("header");

                super.setup();
            }

            @Override
            protected void loop() throws InterruptedException {
                msg.setSeq(sequenceNumber);
                publisher.publish(msg);
                sequenceNumber++;
                Thread.sleep(1000);
            }
        });
    }

    @Override
    public void onShutdown(Node node) {

    }

    @Override
    public void onShutdownComplete(Node node) {

    }

    @Override
    public void onError(Node node, Throwable throwable) {

    }
}

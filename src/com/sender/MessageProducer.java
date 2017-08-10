package com.sender;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageProducer {

	private final static String QUEUE_NAME = "Queue_1";

	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    String message = "Hello Message";
	    for(int i=0;i<10;i++){
	    	String messageTobeSent="";
	    	messageTobeSent=message+"_"+i;
	    	channel.basicPublish("", QUEUE_NAME, null, messageTobeSent.getBytes("UTF-8"));
	    	Thread.sleep(5000);
	    	System.out.println(" [x] Sent '" + messageTobeSent + "'");
	    }
	    channel.close();
	    connection.close();
	  }

	

}

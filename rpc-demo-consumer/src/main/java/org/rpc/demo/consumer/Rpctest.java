package org.rpc.demo.consumer;

import java.net.InetSocketAddress;

import org.rpc.demo.provider.EchoService;
import org.rpc.demo.provider.EchoServiceImpl;
import org.rpc.demo.provider.RpcExporter;

public class Rpctest {
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			public void run() {
				try {
					RpcExporter.exporter("localhost", 8088);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		).start();
		RpcImporter<EchoService> rpcImporter  = new RpcImporter<EchoService>();
		EchoService echo = rpcImporter.importer(EchoServiceImpl.class, new InetSocketAddress("localhost", 8088));
		//EchoService echo =new EchoServiceImpl();
		System.out.println(echo.echo("are you ok?"));
	}

}

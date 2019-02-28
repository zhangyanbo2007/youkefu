package com.ukefu.webim.util.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.util.server.handler.AgentEventHandler;
import com.ukefu.webim.util.server.handler.AiIMEventHandler;
import com.ukefu.webim.util.server.handler.EntIMEventHandler;
import com.ukefu.webim.util.server.handler.IMEventHandler;
  
@Component  
public class ServerRunner implements CommandLineRunner {  
    private final SocketIOServer server;
    private final SocketIONamespace imSocketNameSpace ;
    private final SocketIONamespace agentSocketIONameSpace ;
    private final SocketIONamespace entIMSocketIONameSpace ;
    private final SocketIONamespace aiIMSocketIONameSpace ;
    private final SocketIONamespace callCenterSocketIONameSpace ;
    
    @Autowired  
    public ServerRunner(SocketIOServer server) {  
        this.server = server;  
        imSocketNameSpace = server.addNamespace(UKDataContext.NameSpaceEnum.IM.getNamespace())  ;
        agentSocketIONameSpace = server.addNamespace(UKDataContext.NameSpaceEnum.AGENT.getNamespace()) ;
        entIMSocketIONameSpace = server.addNamespace(UKDataContext.NameSpaceEnum.ENTIM.getNamespace()) ;
        aiIMSocketIONameSpace = server.addNamespace(UKDataContext.NameSpaceEnum.AIIM.getNamespace()) ;
        if(UKDataContext.model.get("callcenter") !=null && UKDataContext.model.get("callcenter") == true){
        	callCenterSocketIONameSpace  = server.addNamespace(UKDataContext.NameSpaceEnum.CALLCENTER.getNamespace()) ;
        }else{
        	callCenterSocketIONameSpace = null ;
        }
    }
    
    @Bean(name="imNamespace")
    public SocketIONamespace getIMSocketIONameSpace(SocketIOServer server ){
    	imSocketNameSpace.addListeners(new IMEventHandler(server));
    	return imSocketNameSpace  ;
    }
    
    @Bean(name="agentNamespace")
    public SocketIONamespace getAgentSocketIONameSpace(SocketIOServer server){
    	agentSocketIONameSpace.addListeners(new AgentEventHandler(server));
    	return agentSocketIONameSpace;
    }

    @Bean(name="entimNamespace")
    public SocketIONamespace getEntIMSocketIONameSpace(SocketIOServer server){
    	entIMSocketIONameSpace.addListeners(new EntIMEventHandler(server));
    	return entIMSocketIONameSpace;
    }
    
    @Bean(name="aiimNamespace")
    public SocketIONamespace getAiIMSocketIONameSpace(SocketIOServer server){
    	aiIMSocketIONameSpace.addListeners(new AiIMEventHandler(server));
    	return aiIMSocketIONameSpace;
    }
    
    @Bean(name="callCenterNamespace")
    public SocketIONamespace getCallCenterIMSocketIONameSpace(SocketIOServer server){
    	if(UKDataContext.model.get("callcenter") !=null && UKDataContext.model.get("callcenter") == true){
    		Constructor<?> constructor;
			try {
				constructor = Class.forName("com.ukefu.webim.util.server.handler.CallCenterEventHandler").getConstructor(new Class[]{SocketIOServer.class});
				callCenterSocketIONameSpace.addListeners(constructor.newInstance(server));
			} catch (NoSuchMethodException | SecurityException
					| ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
    	}
    	return callCenterSocketIONameSpace;
    }

    public void run(String... args) throws Exception { 
        server.start();  
        UKDataContext.setIMServerStatus(true);	//IMServer 启动成功
    }  
}  
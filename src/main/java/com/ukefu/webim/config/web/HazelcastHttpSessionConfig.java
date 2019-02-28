package com.ukefu.webim.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.rpc.AgentTopicListener;
import com.ukefu.webim.service.rpc.CallCenterTopicListener;
import com.ukefu.webim.service.rpc.ClusterMasterListener;
import com.ukefu.webim.service.rpc.ClusterMember;
import com.ukefu.webim.service.rpc.EntIMTopicListener;
import com.ukefu.webim.service.rpc.IMTopicListener;
import com.ukefu.webim.service.rpc.JobDetailListener;
import com.ukefu.webim.service.rpc.NameSpaceTopicListener;

@Configuration
@EnableHazelcastHttpSession(maxInactiveIntervalInSeconds = 3600)
public class HazelcastHttpSessionConfig {
	@Bean
    public HazelcastInstance hazelcastInstance() {
        MapAttributeConfig attributeConfig = new MapAttributeConfig()
                .setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
                .setExtractor(PrincipalNameExtractor.class.getName());

        Config config = new ClasspathXmlConfig("config/hazelcast.xml");

        config.getMapConfig("spring:session:sessions") 
                .addMapAttributeConfig(attributeConfig)
                .addMapIndexConfig(new MapIndexConfig(
                        HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));
        
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
    	hazelcastInstance.getTopic(UKDataContext.UCKeFuTopic.TOPIC_IM.toString()).addMessageListener(new IMTopicListener()) ;
    	hazelcastInstance.getTopic(UKDataContext.UCKeFuTopic.TOPIC_AGENT.toString()).addMessageListener(new AgentTopicListener()) ;
    	hazelcastInstance.getTopic(UKDataContext.UCKeFuTopic.TOPIC_CALLCENTER.toString()).addMessageListener(new CallCenterTopicListener()) ;
    	hazelcastInstance.getTopic(UKDataContext.UCKeFuTopic.TOPIC_ENTIM.toString()).addMessageListener(new EntIMTopicListener()) ;
    	hazelcastInstance.getTopic(UKDataContext.UCKeFuTopic.NAMESPACE.toString()).addMessageListener(new NameSpaceTopicListener()) ;
    	
    	hazelcastInstance.getTopic(UKDataContext.UCKeFuTopic.TOPIC_VOTE.toString()).addMessageListener(new ClusterMasterListener(hazelcastInstance)) ;
    	
    	hazelcastInstance.getTopic(UKDataContext.UCKeFuTopic.TOPIC_JOBDETAIL.toString()).addMessageListener(new JobDetailListener()) ;
    	
    	hazelcastInstance.getCluster().getLocalMember().setLongAttribute("start", System.currentTimeMillis()) ;
    	hazelcastInstance.getCluster().getLocalMember().setStringAttribute("id", UKTools.genID()) ;
    	
    	/**
    	 * 
    	 */
    	hazelcastInstance.getCluster().addMembershipListener(new ClusterMember(hazelcastInstance)) ;
    	
    	/**
    	 * 选出新的 Master服务，然后通知所有服务器
    	 */
    	UKTools.voteMaster(hazelcastInstance);
    	
        return hazelcastInstance; 
    }
}

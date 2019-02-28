package com.ukefu.webim.config.web;

import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;

public class ClusterMembershipListener implements MembershipListener{

	@Override
	public void memberAdded(MembershipEvent membershipEvent) {
		// TODO Auto-generated method stub
		System.out.println("");
	}

	@Override
	public void memberRemoved(MembershipEvent membershipEvent) {
		
	}

	@Override
	public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
		// TODO Auto-generated method stub
		
	}

}

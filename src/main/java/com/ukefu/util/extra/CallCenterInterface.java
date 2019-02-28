package com.ukefu.util.extra;

import com.ukefu.webim.web.model.PbxHost;
import com.ukefu.webim.web.model.PbxHostLog;

public interface CallCenterInterface {
	public PbxHostLog init(PbxHost pbxHost) throws Exception ;
	public void remove(String id) ;
	public boolean connected(String id) ;
}

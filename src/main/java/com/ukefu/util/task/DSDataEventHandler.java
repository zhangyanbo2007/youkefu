package com.ukefu.util.task;

import com.lmax.disruptor.EventHandler;
import com.ukefu.util.UKTools;

public class DSDataEventHandler implements EventHandler<DSDataEvent>
{
	public void onEvent(DSDataEvent event, long sequence, boolean endOfBatch)
    {
		if(event.getDSData().getReport().getId() == null){
			event.getDSData().getReport().setId(UKTools.genID());
			event.getDSData().getReport().setTabledirid(event.getDSData().getTask().getTabledirid());
		}
    	if(event.getDSData().getFile().getName().toLowerCase().endsWith(".xls") || event.getDSData().getFile().getName().toLowerCase().endsWith(".xlsx")){
    		new ExcelImportProecess(event).process(); 
    	}
    }
}
package net.wyun.lottery;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class CaiPiaoHandler_test {

	@Test
	public void testGetActiveCaiPiaos() throws Exception {

			CaiPiaoHandler handler = new CaiPiaoHandler();
	        List<CaiPiao> cps = handler.getActiveCaiPiaos();
	        for(CaiPiao cp:cps){
	        	System.out.println(cp.toString());
	        }
	}

}

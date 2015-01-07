package net.wyun.lottery;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaiPiaoHandler_test {

	@Test
	public void testGetActiveCaiPiaos() throws Exception {

			CaiPiaoHandler handler = new CaiPiaoHandler();
	        handler.init();
	        handler.getActiveCaiPiaos();
	}

}

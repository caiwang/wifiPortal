package net.wyun.lottery;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class CaiPiao_test {

	@Test
	public void testCaiPiao() throws Exception {
		//from order_summary get caipiao QiHao, caipiao XiaDanHao
		//from order_detail get caipiao biaozhima, caipiao num#
		
		URL url = this.getClass().getResource("/order_summary.html");
		File summary = new File(url.getFile());
		
		Document doc = Jsoup.parse(summary, "UTF-8");
		CaiPiaoHandler handler = new CaiPiaoHandler();
		
		List<CaiPiao> cps = handler.getActiveCaiPiaos(doc);
		
		assertEquals(1, cps.size());
		
		for(CaiPiao cp:cps){
			System.out.println(cp.toString());
		}
		
		assertEquals("02050812223114", cps.get(0).getNumber());
		assertEquals("5be1e0c0004057c8600215", cps.get(0).getBiaoZhiMa());
		
		
		
		
		
	}

}

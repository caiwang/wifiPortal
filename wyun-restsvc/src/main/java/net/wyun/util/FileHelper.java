/**
 * 
 */
package net.wyun.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author Xuecheng
 *
 */
public class FileHelper {
	
	/**
	 * Save str to fileName
	 * @param str
	 * @param fileName
	 * @throws IOException
	 */
	public static final void save(String str, String fileName) throws IOException{
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				fileName), "UTF-8"));
		try {
			out.write(str);
		} finally {
			out.close();
		}
	}

}

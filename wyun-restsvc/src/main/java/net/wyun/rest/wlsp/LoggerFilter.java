/**
 * 
 */
package net.wyun.rest.wlsp;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xuecheng
 *
 */
public class LoggerFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerFilter.class); 
	public void destroy() {
		// Nothing to do
	}
 
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
				(HttpServletRequest) request);
		// wrappedRequest.getInputStream().read();
		//
		String method = ((HttpServletRequest) request).getMethod();
		
		if(method.equalsIgnoreCase("DELETE") || method.equalsIgnoreCase("PUT")){
			logger.warn("No delete and put allowed: from " + request.getRemoteAddr());
			return;
		}
		
		String body = IOUtils.toString(wrappedRequest.getReader());
		String url = ((HttpServletRequest)request).getRequestURL().toString();
		logger.info("http url: " + url + ", body: " + body);
		wrappedRequest.resetInputStream();
		chain.doFilter(wrappedRequest, response);
 
	}
 
	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to do
	}
 
	private static class ResettableStreamHttpServletRequest extends
			HttpServletRequestWrapper {
 
		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;
 
		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}
 
 
		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}
 
		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return servletStream;
		}
 
		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getReader(), Charset.forName("UTF-8"));
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return new BufferedReader(new InputStreamReader(servletStream));
		}
		
 
		private class ResettableServletInputStream extends ServletInputStream {
 
			private InputStream stream;
 
			@Override
			public int read() throws IOException {
				return stream.read();
			}
			
			@Override
		    public boolean isFinished() {
		        throw new RuntimeException("Not yet implemented");
		        //return false;
		    }

		    @Override
		    public boolean isReady() {
		        throw new RuntimeException("Not yet implemented");
		        //return false;
		    }

			@Override
			public void setReadListener(ReadListener listener) {

			        throw new RuntimeException("Not yet implemented");
			}

		 
		}
	}
 
	
}

/**
 * 
 */
package net.wyun.rest.wlsp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Xuecheng
 *
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter  {

	 private static final Logger logger = LoggerFactory
	            .getLogger(LoggerInterceptor.class);
	 
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String body = "";
		//String body = IOUtils.toString(wrappedRequest.getReader());
        logger.info("Request:===>" + request.getRequestURL().toString() + ", " + body);
        
        //if returned false, we need to make sure 'response' is sent
        return true;
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
				rawData = IOUtils.toByteArray(this.request.getReader());
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

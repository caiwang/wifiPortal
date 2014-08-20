/**
 * 
 */
package net.wyun.facedetection.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wyun.facedetection.impl.OpenCVDetector;

/**
 * @author Xuecheng
 *
 */
@Service
public class FaceDetectionService {
	private static final Logger logger_c = Logger.getLogger(FundService.class);
	
	public boolean detectFace(String fileName){
		logger_c.debug("java.library.path is " + System.getProperty("java.library.path"));
		logger_c.debug("face detection starts...");
		boolean hasFace = OpenCVDetector.detectFace(fileName);
		logger_c.debug("face detection done. Face found? " + hasFace);
		return hasFace;
	}
}

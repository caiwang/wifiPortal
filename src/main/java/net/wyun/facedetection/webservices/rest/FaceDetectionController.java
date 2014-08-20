/**
 * 
 */
package net.wyun.facedetection.webservices.rest;


import net.wyun.facedetection.services.FaceDetectionService;
import net.wyun.facedetection.web.utils.WYunHeader;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import net.wyun.facedetection.web.utils.AuthorizationException;

/**
 * @author Xuecheng
 *
 */
@Controller
//@RequestMapping("/detection")
public class FaceDetectionController {
	private static final Logger logger = Logger.getLogger(FaceDetectionController.class);
	
	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";
	
	@Autowired
	private View jsonView_i;
	
	@Autowired
	private FaceDetectionService faceDetectionService;

	public void setFaceDetectionService(FaceDetectionService faceDetectionService) {
		this.faceDetectionService = faceDetectionService;
	}
	
	@RequestMapping(value = "/detection/{user_Id}/{imgFileName}", 
			method = RequestMethod.GET
			/*headers = WYunHeader.PROTOCOL_VERSION + "=1"*/)
	public ModelAndView  readImage(			
			@PathVariable("user_Id") String user_Id,
			@PathVariable("imgFileName") String imgFileName,			
			@RequestHeader(WYunHeader.METADATA) String metadata,
			@RequestHeader(WYunHeader.USER) String user,
			@RequestHeader(WYunHeader.PASSWORD) String password) 
	{
		
		long start = System.currentTimeMillis();
		
		boolean hasFace = false;
		// authorize username/pass
		try {
			if ( !checkUserPass(user, password)) {
				logger.info("Rejecting credentials");
				AuthorizationException exception = new AuthorizationException("Unrecognized Username/Password");
				throw exception;
			}
		} catch (RuntimeException e) {
			throw new AuthorizationException("Unrecognized Username/Password: " + user + "/" + password);
		}
		
		try {
			hasFace = this.faceDetectionService.detectFace(imgFileName);
			
		} catch (Exception e) {
			
			// pass to the generic exception handler
			logger.warn("Error occured: " + e.getMessage());
			String sMessage = "Error invoking face detection service. [%1$s]";
			return createErrorResponse(String.format(sMessage, e.toString()));
		} 
		
		return new ModelAndView(jsonView_i, DATA_FIELD, hasFace);
	}
	
	/**
	 * Create an error REST response.
	 *
	 * @param sMessage
	 *            the s message
	 * @return the model and view
	 */
	private ModelAndView createErrorResponse(String sMessage) {
		return new ModelAndView(jsonView_i, ERROR_FIELD, sMessage);
	}
	
	
	private static final String HARD_USER = "qhd";
	private static final String HARD_PASS = "detecti$3";
	private boolean checkUserPass(String user, String password) {
		
		logger.debug("User: !" + user + "!    Pass: !" + password + "!");
		return user.equalsIgnoreCase(HARD_USER) && password.equals(HARD_PASS);
	}

}

package net.wyun.wlsp.integration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import net.wyun.wlsp.data.TestData;
import net.wyun.rest.wlsp.client.VideoSvcApi;
import net.wyun.rest.wlsp.json.ResourcesMapper;
import net.wyun.rest.wlsp.repository.Video;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.JacksonConverter;

/**
 * 
 * This integration test sends a POST request to the VideoServlet to add a new video 
 * and then sends a second GET request to check that the video showed up in the list
 * of videos. Actual network communication using HTTP is performed with this test.
 * 
 * The test requires that the VideoSvc be running first (see the directions in
 * the README.md file for how to launch the Application).
 * 
 * To run this test, right-click on it in Eclipse and select
 * "Run As"->"JUnit Test"
 * 
 * Pay attention to how this test that actually uses HTTP and the test that just
 * directly makes method calls on a VideoSvc object are essentially identical.
 * All that changes is the setup of the videoService variable. Yes, this could
 * be refactored to eliminate code duplication...but the goal was to show how
 * much Retrofit simplifies interaction with our service!
 * 
 * @author jules
 *
 */
public class VideoSvcClientApiTest {

	private final String TEST_URL = "http://localhost:8080";
	
	JacksonConverter converter = new JacksonConverter(new ResourcesMapper());

	private VideoSvcApi videoService = new RestAdapter.Builder().setConverter(converter)
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(VideoSvcApi.class);

	private Video video = TestData.randomVideo();
	
	/**
	 * This test creates a Video, adds the Video to the VideoSvc, and then
	 * checks that the Video is included in the list when getVideoList() is
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVideoAddAndList() throws Exception {
	
		try {
			// Add the video
			videoService.addVideo(video);
		} catch (RetrofitError e) {
			// the returned http response with empty body, the jackson converter
			// doesn't handle it correctly
			// for current Retrofit version, we igonore the CONVERSION error
			if (RetrofitError.Kind.CONVERSION == e.getKind()) {
				System.out
						.print("Conversion error due to empty http body, ignore!");
			} else {
				throw new Exception(e);
			}
		
    	}
		
		// We should get back the video that we added above
		Collection<Video> videos = videoService.getVideoList();
		assertTrue(videos.contains(video));
	}

}

package dmit2015.wxue3.assignment05.ejb;
/**
 * This java class set the automatic timer to download file from specific website to designated folder every weekday at 4pm
 *
 * @author  Wanlun Xue
 * @version 1.0
 * @lastModified   2021.04.02
 */
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.logging.Logger;

@Singleton		// Instruct the container to create a single instance of this EJB
@Startup		// Create this EJB is created when this app starts
public class AutomaticTimersBean {	// Also known as Calendar-Based Timers

	private Logger _logger = Logger.getLogger(AutomaticTimersBean.class.getName());

	/**
	 * Assuming you have define the following entries in your web.xml file
	 *     <env-entry>
	 *         <env-entry-name>ca.dmit2015.config.SYSADMIN_EMAIL</env-entry-name>
	 *         <env-entry-type>java.lang.String</env-entry-type>
	 *         <env-entry-value>yourUsername@yourEmailServer</env-entry-value>
	 *     </env-entry>
	 */
	@Resource(name="ca.dmit2015.config.SYSADMIN_EMAIL")

	private void downloadCsv(Timer timer) {

		HttpClient client = HttpClient.newHttpClient();
		HashMap<String, String> info = (HashMap<String, String>) timer.getInfo();
		String downloadUriString = "https://data.edmonton.ca/api/views/ix8f-s9xp/rows.csv?accessType=DOWNLOAD";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(downloadUriString))
				.build();
		String downloadDirectory = "/home/user2015/Downloads";
		Path downloadPath = Path.of(downloadDirectory);
		try {
			HttpResponse<Path> response = client.send(request,
					HttpResponse.BodyHandlers.ofFileDownload(downloadPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE));
			_logger.info("Finished download file to " + response.body());
		} catch (Exception e) {
			_logger.fine("Error downloading file. " + e.getMessage());
			e.printStackTrace();
		}

	}


	@Schedules({
		@Schedule(second = "0", minute ="00", hour = "16", dayOfWeek = "Mon,Tue,Wed,Thu,Fri", month = "Jan-Dec", year = "2021-2030", persistent = false),
			@Schedule(second = "0", minute ="00", hour = "2", dayOfWeek = "Mon,Tue,Wed,Thu,Fri", month = "Jan-Dec", year = "2021-2030", persistent = false),
		})
	public void downloadCsvToLocalMachine(Timer timer) {
		downloadCsv(timer);
	}

}
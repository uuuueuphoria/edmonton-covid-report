package dmit2015.wxue3.assignment05.ejb;
/**
 * This java class set the automatic timer to download file from specific website to designated folder every weekday at 4pm
 *
 * @author  Wanlun Xue
 * @version 1.0
 * @lastModified   2021.04.02
 */
import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.*;
import javax.inject.Inject;
import javax.mail.MessagingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Logger;

@Singleton		// Instruct the container to create a single instance of this EJB
@Startup		// Create this EJB is created when this app starts
public class AutomaticTimersBean {	// Also known as Calendar-Based Timers

	private Logger _logger = Logger.getLogger(AutomaticTimersBean.class.getName());


	@Resource(name="ca.dmit2015.config.DOWNLOAD")
	private String location;
	public void downloadCsv(Timer timer) {

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
			String batchJobXmlFilename = location;
			// Get the JobOperator from the BatchRuntime
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			// Create a new job instance and start the first execution of that instance asynchronously.
			long executionId = jobOperator.start(batchJobXmlFilename, null);
			_logger.info("Successfully started batch job with executionId " + executionId);
		} catch (Exception e) {
			_logger.fine("Error downloading file. " + e.getMessage());
			e.printStackTrace();
		}



	}



	@Schedule(second = "0", minute ="00", hour = "16", dayOfWeek = "Mon,Tue,Wed,Thu,Fri", month = "Jan-Dec", year = "2021-2030", persistent = false)
	public void downloadCsvToLocalMachine(Timer timer) {
		downloadCsv(timer);
	}

}
package dmit2015.wxue3.assignment05.batch;
/**
 * This is the job listener class. It listens and logs the status before and after the job
 *
 * @author  Wanlun Xue
 * @version 1.0
 * @lastModified   2021.04.02
 */

import dmit2015.wxue3.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;

import javax.batch.api.listener.AbstractJobListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;


/**
 * This executes before and after a job execution runs.
 * To apply this listener to a batch job you must define a listener element in the Job Specification Language (JSL) file
 * BEFORE the step element as follows:
 *
 * <listeners>
 * <listener ref="jobListener" />
 * </listeners>
 */
@Named
public class CurrentCasesByLocalGeographicAreaJobListener extends AbstractJobListener {

    @Inject
    private JobContext _jobContext;

    @Inject
    private Logger _logger;

    private long startTime;

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _repository;

    @Override
    public void beforeJob() throws Exception {
        _logger.info(_jobContext.getJobName() + " beforeJob");
        startTime = System.currentTimeMillis();

        // Delete all records from the data source as we need to re-create all the records
        _repository.deleteAll();

    }

    @Override
    public void afterJob() throws Exception {
        _logger.info(_jobContext.getJobName() + "afterJob");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        String message = _jobContext.getJobName() + " completed in " + duration + " milliseconds";
        _logger.info(message);

    }

}
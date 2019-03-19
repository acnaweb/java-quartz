package br.com.marketming.study_quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobsEncadeados implements Job {

	private JobDetail nextJob;

	public void setNextJob(JobDetail nextJob) {
		this.nextJob = nextJob;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

	}

}

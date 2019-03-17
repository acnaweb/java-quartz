package br.com.marketming.study_quartz;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AcaoASerExecutada implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(String.format("Executando algo em %s", LocalDateTime.now()));

	}

}

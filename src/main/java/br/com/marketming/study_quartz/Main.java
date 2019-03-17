package br.com.marketming.study_quartz;

import java.time.LocalDateTime;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Main {
	// http://www.nncron.ru/help/EN/working/cron-format.htm
	//  <Minute> <Hour> <Day_of_the_Month> <Month_of_the_Year> <Day_of_the_Week> <Year>
	private static final String CRON_SEGUNDO_A_SEGUNDO = "0/1 * * * * ?";
	private static final String CRON_CADA_10_SEGUNDOS = "0/10 * * * * ?";
	private static final String CRON_DIARIO_22H = "15 22 * * * ?";

	
	public static void main(String[] args) {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();
			
			// criacao do job
			JobDetail job = JobBuilder.newJob(AcaoASerExecutada.class)
                    .withIdentity("acao1", "grupo1")
                    .build();
			
			// criacao do trigger que vai dispara a acao
			
			Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("triggerMinutoAMinuto","grupo1")
                    // .withSchedule(CronScheduleBuilder.cronSchedule(CRON_DIARIO_22H))
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(22, 19))
                    .build();
			
			// inicia o processo
			scheduler.scheduleJob(job, trigger);
			
			System.out.println(String.format("Iniciando em %s", LocalDateTime.now()));
			scheduler.start();

		} catch (Exception e) {
		}

	}

}

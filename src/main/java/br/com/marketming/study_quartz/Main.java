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
	// <Minute> <Hour> <Day_of_the_Month> <Month_of_the_Year> <Day_of_the_Week>
	// <Year>
	private static final String CRON_CADA_1_SEGUNDO = "0/1 * * * * ?";
	private static final String CRON_CADA_5_SEGUNDOS = "0/5 * * * * ?";
	private static final String CRON_CADA_10_SEGUNDOS = "0/10 * * * * ?";

	public static void main(String[] args) {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();

			// criacao do job
			JobDetail job1 = JobBuilder.newJob(AcaoASerExecutada.class).withIdentity("job1", "grupo1").build();
			
			JobDetail job2 = JobBuilder.newJob(AcaoASerExecutada.class).withIdentity("job2", "grupo1").build();

			// criacao do trigger que vai dispara a acao
			Trigger triggerHorarioFixo = TriggerBuilder.newTrigger().withIdentity("triggerHorarioFixo", "grupo1")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(22, 36)).build();
					// .withSchedule(CronScheduleBuilder.cronSchedule(CRON_CADA_1_SEGUNDO)).build();


			Trigger triggerComIntervalo = TriggerBuilder.newTrigger().withIdentity("triggerComIntervalo", "grupo1")
					.withSchedule(CronScheduleBuilder.cronSchedule(CRON_CADA_10_SEGUNDOS)).build();

			// inicia o processo
			System.out.println(String.format("Iniciando em %s", LocalDateTime.now()));
			
			scheduler.start();
			scheduler.scheduleJob(job1, triggerHorarioFixo);
			scheduler.scheduleJob(job2, triggerComIntervalo);
					

		} catch (Exception e) {
		}

	}

}

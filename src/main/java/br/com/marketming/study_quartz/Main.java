package br.com.marketming.study_quartz;

import java.time.LocalDateTime;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Main {

	public static void main(String[] args) {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			Scheduler scheduler = factory.getScheduler();

			// criacao do job
			JobDetail job1 = JobBuilder.newJob(AcaoASerExecutada.class).withIdentity("job1", "grupo1").build();

			JobDetail job2 = JobBuilder.newJob(AcaoASerExecutada.class).withIdentity("job2", "grupo1").build();

			JobDetail job3 = JobBuilder.newJob(AcaoASerExecutada.class).withIdentity("job3", "grupo1").build();

			Trigger triggerHorarioFixo = TriggerBuilder.newTrigger().withIdentity("triggerHorarioFixo", "grupo1")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(22, 36)).build();

			Trigger triggerComIntervalo = TriggerBuilder.newTrigger().withIdentity("triggerComIntervalo", "grupo1")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(2).repeatForever())
					.build();

			Trigger triggerSobDemanda = TriggerBuilder.newTrigger().withIdentity("triggerSobDemanda", "grupo1")
					.startNow().build();

			// inicia o processo
			System.out.println(String.format("iniciando em %s", LocalDateTime.now()));

			scheduler.start();
			scheduler.scheduleJob(job1, triggerHorarioFixo);
			scheduler.scheduleJob(job2, triggerComIntervalo);
			scheduler.scheduleJob(job3, triggerSobDemanda);

			//

		} catch (Exception e) {
		}

	}

}

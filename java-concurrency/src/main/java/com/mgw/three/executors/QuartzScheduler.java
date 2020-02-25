package com.mgw.three.executors;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzScheduler  {

    class SimpleJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("======"+System.currentTimeMillis());
        }
    }


    public static void main(String[] args) throws SchedulerException {

        JobDetail job = newJob(SimpleJob.class).
                withIdentity("Job1","Group1").build();

        Trigger trigger = newTrigger().withIdentity("Trigger1","Group1").
                withSchedule(cronSchedule("0/5 * * * * ?")).build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job,trigger);

    }
}

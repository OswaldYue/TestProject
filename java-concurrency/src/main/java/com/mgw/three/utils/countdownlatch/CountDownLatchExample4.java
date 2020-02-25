package com.mgw.three.utils.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用CountDownLatch 打散任务
 * */
public class CountDownLatchExample4 {

    private static final Random random = new Random(System.currentTimeMillis());

    static class Event {
        int id = 0;

        public Event(int id) {
            this.id = id;
        }
    }

    interface Watcher {
//        void startWatch();
        void done(Table table);
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch latch;

        private TaskGroup taskGroup;

        public TaskBatch(int size ,TaskGroup taskGroup) {

            this.latch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

//        @Override
//        public void startWatch() {
//
//        }

        @Override
        public void done(Table table) {
            latch.countDown();
            if (latch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work ,[" + table + "]");
                // 通知group其中的一个表完成
                taskGroup.done(table);
            }
        }
    }


    static class TaskGroup implements Watcher {

        private CountDownLatch latch ;

        private Event event;

        public TaskGroup(int size,Event event) {
            this.event = event;
            this.latch = new CountDownLatch(size);
        }

        @Override
        public void done(Table table) {
            latch.countDown();
            if (latch.getCount() == 0) {
                System.out.println("====All of table done in event : " + event.id);
            }
        }
    }

    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumnSchema = "<table name='a'><column name='col1' type=varchar /></table>";
        String targetColumnSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumnSchema='" + sourceColumnSchema + '\'' +
                    ", targetColumnSchema='" + targetColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event event) {
        List<Table> list = new ArrayList<>();

        for (int i = 0 ; i < 10 ; i++) {
            list.add(new Table("table-" + event.id + "-" + i,i * 100));

        }
        return list;
    }


    static class TrustSourceRecordCount implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        TrustSourceRecordCount(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            table.targetCount = table.sourceRecordCount;
//            System.out.println("The table " + table.tableName + " target record count capture done and update the data.");

            taskBatch.done(table);
        }
    }

    static class TrustSourceColumns implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        TrustSourceColumns(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(random.nextInt(10_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            table.targetColumnSchema = table.sourceColumnSchema;
//            System.out.println("The table " + table.tableName + " target colums capture done and update the data.");

            taskBatch.done(table);
        }

    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Event[] events = {new Event(1), new Event(2)};

        for (Event event : events) {
            List<Table> tables = capture(event);
            TaskGroup taskGroup = new TaskGroup(tables.size(),event);
            for (Table table : tables) {
                TaskBatch taskBatch = new TaskBatch(2,taskGroup);
                TrustSourceRecordCount trustSourceRecordCount = new TrustSourceRecordCount(table, taskBatch);
                TrustSourceColumns trustSourceColumns = new TrustSourceColumns(table,taskBatch);

                executorService.submit(trustSourceRecordCount);
                executorService.submit(trustSourceColumns);
            }
        }

    }

}

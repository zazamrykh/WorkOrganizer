package com.example.workorganizer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransferTasks {
    public static void transfer(TableChronology fromTableChron, TableChronology toTableChron) {
        ObservableList<Task> fromList = fromTableChron.getData();
        ObservableList<Task> toList = FXCollections.observableArrayList();
        HashMap<WeekDays, Integer> workload = new HashMap<>();
        for (WeekDays weekDay : WeekDays.getValues()){
            workload.put(weekDay, 0);
        }

        Comparator<Task> comparatorOfTime = (task1, task2) -> {
            int workload1 = Integer.parseInt(task1.getAllTime());
            int workload2 = Integer.parseInt(task2.getAllTime());
            return workload2 - workload1;
        };

        fromList.sort(comparatorOfTime);

        for (Task task :fromList) {
            insertLessWorkload(task, toList, workload);
        }

        toTableChron.getData().setAll(toList);
    }

    private static void insertLessWorkload(Task task, ObservableList<Task> toList, HashMap<WeekDays, Integer> workload) {
        WeekDays lessWorkloadDayNumber = findLessWorkloadDay(workload);
        task.setDay(lessWorkloadDayNumber.toString());
        toList.add(task);
        Integer lessWorkloadDayWorkload = workload.get(lessWorkloadDayNumber);
        int taskWorkload = Integer.parseInt(task.getAllTime());
        if (lessWorkloadDayWorkload == null) {
            lessWorkloadDayWorkload = taskWorkload;
        } else {
            lessWorkloadDayWorkload += taskWorkload;
        }
        workload.put(lessWorkloadDayNumber, lessWorkloadDayWorkload);
    }

    private static WeekDays findLessWorkloadDay(HashMap<WeekDays, Integer> workload) {
        WeekDays lessWorkloadDay = null;
        Integer lessWorkloadDayWorkload = 0;
        for (Map.Entry<WeekDays, Integer> day: workload.entrySet()) {
            if (lessWorkloadDay == null) {
                lessWorkloadDay = day.getKey();
                lessWorkloadDayWorkload = day.getValue();
                continue;
            }

            if (lessWorkloadDayWorkload > day.getValue()) {
                lessWorkloadDay = day.getKey();
                lessWorkloadDayWorkload = day.getValue();
            }
        }
        return lessWorkloadDay;
    }
}

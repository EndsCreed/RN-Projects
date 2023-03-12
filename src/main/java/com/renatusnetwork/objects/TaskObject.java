package com.renatusnetwork.objects;

import com.renatusnetwork.Enums.*;

import java.util.List;

public class TaskObject {
    String name;
    String desc;
    TaskType type;
    Division division;
    TaskPriority priority;
    TaskStatus status;
    List<PlayerStats> assigned;
    List<String> updates;


}

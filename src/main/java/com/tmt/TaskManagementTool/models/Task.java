package com.tmt.TaskManagementTool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document( collection = "Task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    private ObjectId id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String dueDate;
    private String assignee;
    private String assignedTo;
    private String assignedBy;
    private String assignedDate;
    private String assignedTime;
    private String dueTime;
    private String comments;
    private String taskType;
    private String taskCategory;
    private String attachments;
    
}

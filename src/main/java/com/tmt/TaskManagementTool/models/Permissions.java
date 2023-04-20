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
public class Permissions {

    @Id
    private ObjectId id;
    private String permission_id;
    private String permission_name;
    
}

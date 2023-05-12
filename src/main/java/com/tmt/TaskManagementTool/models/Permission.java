package com.tmt.TaskManagementTool.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document( collection = "Permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    private ObjectId id;
    private String pid;
    private String name;
    
}

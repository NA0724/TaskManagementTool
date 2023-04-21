package com.tmt.TaskManagementTool.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document( collection = "Roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    private ObjectId id;
    private String role_id;
    private String role_name;
    @DocumentReference
    private List<Permissions> permissions;
    
}

// import React, { useState } from 'react';
// import { TextField, Button } from '@mui/material';

import React, { useState, useEffect } from "react";
import {
  TextareaAutosize,
  TextField,
  Button,
  Typography,
  Box,
  Select,
  MenuItem,
  InputLabel,
  CircularProgress,
} from "@mui/material";

interface AddTaskProps {
  onCancel: () => void;
}
interface User {
  id: {
    timestamp: number;
    date: string;
  };
  username: string;
  password: string;
  email: string;
  firstname: string;
  lastname: string;
  role: string | null;
  permission: string | null;
}
const AddTask: React.FC<AddTaskProps> = ({ onCancel }) => {
  const [users, setUsers] = useState<User[]>([]);
  const [selectedUser, setSelectedUser] = useState(
    users.length > 0 ? users[0].username : ""
  );

  const [loading, setLoading] = useState(false);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [priority, setPriority] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [assignee, setAssignee] = useState("");
  const [assignedTo, setAssignedTo] = useState("");
  const [assignedBy, setAssignedBy] = useState("");
  const [assignedDate, setAssignedDate] = useState("");
  const [assignedTime, setAssignedTime] = useState("");
  const [dueTime, setDueTime] = useState("");
  const [comment, setComment] = useState("");
  const [taskType, setTaskType] = useState("");
  const [taskCategory, setTaskCategory] = useState("General");
  const [status, setStatus] = useState("New");
  const [attachment, setAttachment] = useState<string>("");
  const [selectedDate, setSelectedDate] = useState<string>("");

  const user = sessionStorage.getItem("user");
  var currentTimestamp: number = new Date().getTime();
  const id = String(currentTimestamp).slice(-9);
  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/users", {
        mode: "cors",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      });
      const data = await response.json();
      setUsers(data);
      setSelectedUser(data[0].userName);
    } catch (error) {
      console.error("Error fetching users:", error);
    }
  };

  const handleDateChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedDate(event.target.value);
    setDueDate(event.target.value);
  };

  const handleAttachmentChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    if (event.target.files) {
      setAttachment(event.target.files[0].name);
    }
  };

  const handleSubmit = async () => {
    setLoading(true);
    var tid = `${id}`;
    console.log("handle Submit of Add Task");
    var comments = [{ body: comment }];
    var attachments = [{ body: attachment }];
    const formData = {
      title,
      description,
      priority,
      dueDate,
      assignedTo,
      taskType,
      taskCategory,
      attachments,
      comments,
      status,
      tid,
    };

    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/tasks/create-task",
        {
          method: "POST",
          mode: "cors",
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
          },
          body: JSON.stringify(formData),
        }
      );
      const data = await response.json();
      console.log(data);
      setLoading(false);
    } catch (error) {
      setLoading(false);
      console.error("Error fetching users:", error);
    }

    onCancel();
  };

  return (
    <Box display="flex" flexDirection="column" height="100%">
      <Typography variant="h6" component="div" gutterBottom>
        Add Task
      </Typography>

      <Box flexGrow={1} display="flex" flexDirection="column">
        <form onSubmit={handleSubmit}>
          <InputLabel id="priority-label">Title</InputLabel>
          <TextField
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            fullWidth
          />
          <InputLabel id="priority-label">Description</InputLabel>
          <TextareaAutosize
            minRows={3}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            style={{ width: "100%" }}
          />
          <InputLabel id="priority-label">Priority</InputLabel>
          <Select
            value={priority}
            onChange={(e) => setPriority(e.target.value as string)}
            fullWidth
          >
            <MenuItem value="High">High</MenuItem>
            <MenuItem value="Low">Low</MenuItem>
            <MenuItem value="Medium">Medium</MenuItem>
          </Select>
          <InputLabel id="priority-label">DueDate</InputLabel>
          <input
            type="date"
            value={selectedDate}
            onChange={handleDateChange}
            className="datepicker-input"
          />
          <InputLabel id="priority-label">AssingedTo</InputLabel>
          <Select
            labelId="user-label"
            value={selectedUser}
            onChange={(e) => {
              setSelectedUser(e.target.value);
              setAssignedTo(e.target.value);
            }}
            fullWidth
          >
            {users.map((user) => (
              <MenuItem value={user.username}>{user.username}</MenuItem>
            ))}
          </Select>
          <InputLabel id="priority-label">Task Type</InputLabel>
          <TextField
            value={taskType}
            onChange={(e) => setTaskType(e.target.value)}
            fullWidth
          />
          <InputLabel id="priority-label">Comments</InputLabel>
          <TextareaAutosize
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            style={{ width: "100%" }}
            minRows={3}
          />
          <InputLabel id="priority-label">attachment</InputLabel>
          <input type="file" onChange={handleAttachmentChange} multiple />
        </form>
      </Box>

      <Box marginTop="auto">
        {loading ? (
          <CircularProgress />
        ) : (
          <Button onClick={handleSubmit}>Submit</Button>
        )}
        <Button onClick={onCancel}>Cancel</Button>
      </Box>
    </Box>
  );
};

export default AddTask;
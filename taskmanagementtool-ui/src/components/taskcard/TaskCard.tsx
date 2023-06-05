import React from "react";
import { Paper, Box, Typography } from "@mui/material";
import { styled } from "@mui/system";
import { Link } from "react-router-dom";

interface TaskCardProps {
  tid: string;
  title: string;
  description?: string;
  status?: string;
  priority?: string;
  dueDate?: string;
  createdBy?: string;
  createdAt?: string;
  assignedTo?: string;
  assignedBy?: string;
  assignedDate?: string;
  assignedTime?: string;
  dueTime?: string;
  taskType?: string;
  taskCategory?: string;
  comments?: Comment[];
  attachments?: null | File[];
}

const TaskCard: React.FC<TaskCardProps> = ({
  tid,
  title,
  description,
  status,
  priority,
  dueDate,
  createdBy,
  createdAt,
  assignedTo,
  assignedBy,
  assignedDate,
  assignedTime,
  dueTime,
  taskType,
  taskCategory,
  comments,
  attachments,
}) => {
  return (
    <Paper elevation={3} sx={{ padding: "1rem", marginBottom: "1rem" }}>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          marginBottom: "0.5rem",
        }}
      >
        <Typography variant="h6" component="div">
          <Link
            to={`/task/${tid}`} // Append tid to the URL
            style={{ textDecoration: "hyperlink", color: "inherit" }}
          >
            Task:{tid}
          </Link>
        </Typography>
      </Box>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          marginBottom: "0.5rem",
        }}
      >
        <Typography variant="body2" component="div">
          Title: {title}
        </Typography>
      </Box>
      <Box
        sx={{
          display: "flex",
          alignItems: "left",
          justifyContent: "space-between",
          marginBottom: "0.5rem",
        }}
      >
        <Typography variant="body2" component="div">
          Description: {description}
        </Typography>
        <Typography variant="body2" component="div">
          Priority: {priority}
        </Typography>
      </Box>
      <Box
        sx={{
          display: "flex",
          alignItems: "left",
          justifyContent: "space-between",
          marginBottom: "0.5rem",
        }}
      >
        <Typography variant="body2" component="div">
          Owner: {assignedTo}
        </Typography>
        <Typography variant="body2" component="div">
          Status: {status}
        </Typography>
      </Box>
      <Box
        sx={{
          display: "flex",
          alignItems: "left",
          justifyContent: "space-between",
          marginBottom: "0.5rem",
        }}
      >
        <Typography variant="body2" component="div">
          Due Date: {dueDate}
        </Typography>
        <Typography variant="body2" component="div">
          Category: {taskCategory}
        </Typography>
      </Box>
    </Paper>
  );
};

export default TaskCard;

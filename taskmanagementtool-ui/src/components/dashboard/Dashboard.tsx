import React, { useEffect, useState } from "react";
import {
  Typography,
  Grid,
  Paper,
  AppBar,
  Toolbar,
  IconButton,
  Button,
  InputBase,
  Badge,
  Drawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
  Tooltip,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Box,
  TextField,
  Stack,
  Backdrop,
  CircularProgress,
} from "@mui/material";
import {
  Search,
  AddCircleOutline,
  Notifications,
  Dashboard as DashboardIcon,
  Assignment,
  Settings,
  Group,
  Assessment,
  AccountCircle,
  Logout,
  Inventory2,
  Close,
  Menu as MenuIcon,
} from "@mui/icons-material";
import "./Dashboard.css";
import { styled } from "@mui/system";
import { Link } from "react-router-dom";
import TaskCard from "../taskcard/TaskCard";
import AddTask from "../addtask/AddTask";
import NotificationPane from "../notification/NotificationPane";

// Import the background image
import backgroundImage from "../images/background.jpg";

interface Task {
  id: string;
  tid: string;
  title: string;
  description: string;
  status: string;
  priority: string;
  dueDate: string;
  createdBy: string;
  createdAt: string;
  assignedTo: string;
  assignedBy: string;
  assignedDate: string;
  assignedTime: string;
  dueTime: string;
  taskType: string;
  taskCategory: string;
  comments: Comment[];
  attachments: null | File[];
}

interface Comment {
  id: string;
  text: string;
  createdAt: string;
  createdBy: string;
}

interface NotificationPaneProps {
  notifications: string[];
  onClose: () => void;
}

const Dashboard: React.FC = () => {
  // Retrieve the user value from sessionStorage
  const user = sessionStorage.getItem("user");

  // Use the user value as needed in your component
  console.log(user); // Example: Output the user value to the console

  const [isDrawerOpen, setIsDrawerOpen] = React.useState(false);
  const [newTaskCount, setnewTaskCount] = React.useState(0);
  const [inProgressTasksCount, setinProgressTasksCount] = React.useState(0);
  const [completedTasksCount, setcompletedTasksCount] = React.useState(0);
  const [loading, setLoading] = useState(true);
  const toggleDrawer =
    (open: boolean) => (event: React.KeyboardEvent | React.MouseEvent) => {
      if (
        event &&
        event.type === "keydown" &&
        ((event as React.KeyboardEvent).key === "Tab" ||
          (event as React.KeyboardEvent).key === "Shift")
      ) {
        return;
      }

      setIsDrawerOpen(open);
    };

  const drawerItems = [
    { text: "Profile", icon: <AccountCircle />, route: "/profile" },
    { text: "Dashboard", icon: <DashboardIcon />, route: "/dashboard" },
    { text: "Report", icon: <Assessment />, route: "/report" },
    { text: "All Users", icon: <Group />, route: "/userList" },
    { text: "All Tasks", icon: <Inventory2 />, route: "/taskList" },
  ];

  const [selectedOption, setSelectedOption] = useState("");

  const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedOption(event.target.value);
  };

  const [isOpen, setIsOpen] = useState(false);
  const [isNotificationPaneOpen, setNotificationPaneOpen] = useState(false);

  const handleAddButtonClick = () => {
    // Handle the button click event here
    console.log("Button clicked!");
    setIsOpen(true);
  };

  const handleClose = () => {
    setIsOpen(false);
  };

  const handleSubmit = () => {
    console.log("Submit Clicked in dashboard");
    // Handle form submission logic here'
    // console.log("Title:", title);
    // console.log("Description:", description);
    // console.log("Priority:", priority);
    // console.log("Submit Clicked in dashboard");
  };

  const handleNotificationClick = () => {
    console.log("Notification clicked!");
    setNotificationPaneOpen(true);
  };

  const handleNotificationClose = () => {
    setNotificationPaneOpen(false);
  };

  const handleLogout = () => {
    console.log("Logout clicked!");
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("userAuth");
    window.location.href = "/login";
  };

  const [tasks, setTasks] = useState<Task[]>([]);
  const [notifications, setNotifications] = useState<string[]>([]);

  useEffect(() => {
    fetchNotifications();
  }, []);

  const fetchNotifications = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/v1/home/dashboard`,
        {
          mode: "cors",
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
          },
        }
      );
      const data = await response.json();
      setTasks(data.tasksAssignedTo);
      setcompletedTasksCount(data.completedTasksCount);
      setinProgressTasksCount(data.inProgressTasksCount);
      setnewTaskCount(data.newTaskCount);
      const bodyElements = data.notifications.map((d: any) => d.body);
      console.log(bodyElements);
      setNotifications(bodyElements);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching notifications:", error);
      setLoading(false);
    }
  };

  return (
    <div className="dashboard-container" style={{ backgroundImage: `url(${backgroundImage})`, backgroundSize: 'cover', minHeight: '100vh', position: 'relative' }}>
      
      <AppBar position="static" className="app-bar">
        <Toolbar>
          <IconButton
            color="inherit"
            size="large"
            onClick={toggleDrawer(true)}
            edge="start"
            className="menu-button"
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Welcome, {user}!
          </Typography>

          <div>
            <IconButton
              color="inherit"
              size="large"
              onClick={handleAddButtonClick}
            >
              <AddCircleOutline />
            </IconButton>
            <Drawer anchor="right" open={isOpen} onClose={handleClose}>
              <Box sx={{ width: 700, padding: "1rem" }}>
                <AddTask onCancel={handleClose} />
              </Box>
            </Drawer>
          </div>

          <div className="search-bar">
            <IconButton
              color="inherit"
              size="large"
              onClick={handleNotificationClick}
            >
              <Tooltip title="Notifications">
                <Badge badgeContent={notifications.length} color="error">
                  <Notifications />
                </Badge>
              </Tooltip>
            </IconButton>
            <Drawer
              anchor="right"
              open={isNotificationPaneOpen}
              onClose={handleNotificationClose}
            >
              <Box sx={{ width: 400, padding: "1rem" }}>
                <NotificationPane
                  notifications={notifications}
                  onClose={handleNotificationClose}
                />
              </Box>
            </Drawer>

            <IconButton color="inherit" size="large">
              <Search />
            </IconButton>
            <InputBase
              placeholder="Search..."
              inputProps={{ "aria-label": "search" }}
              sx={{ ml: 1, color: "inherit", width: "100px" }}
            />
          </div>
          <IconButton color="inherit" size="large" onClick={handleLogout}>
            <Logout />
          </IconButton>
        </Toolbar>
      </AppBar>

     
      {/* SIDE MENU GRID */}
      <Grid container spacing={3} className="content-container">
        <Grid item xs={3} className="drawer-container">
          <Drawer
            anchor="left"
            open={isDrawerOpen}
            onClose={toggleDrawer(false)}
          >
            <Box sx={{ width: 300, padding: "1rem" }}>
              <List>
                {drawerItems.map((item, index) => (
                  <ListItem button key={index} component={Link} to={item.route}>
                    <ListItemIcon>{item.icon}</ListItemIcon>
                    <ListItemText primary={item.text} />
                  </ListItem>
                ))}
              </List>
            </Box>
          </Drawer>
        </Grid>
      </Grid>

      {/* Top Row */}
      <Grid item xs={3}>
        <Box display="flex" alignItems="center" mb={2} paddingTop={'50px'}>
          <Typography variant="h3" component="span" fontWeight="bold">
            My Tasks
          </Typography>
        </Box>
      </Grid>
      <Backdrop open={loading} style={{ zIndex: 999 }}>
        <CircularProgress color="inherit" />
      </Backdrop>
      {/* Tasks Grid - Takes 70% of the width */}

      <Grid container spacing={10} paddingLeft={'80px'} paddingRight={'80px'}>
        <Grid item xs={10}>
          <Grid container spacing={3}>
            {tasks.map((task) => (
              <Grid item xs={3} key={task.id}>
                <TaskCard
                  tid={task.tid}
                  title={task.title}
                  priority={task.priority}
                  assignedTo={task.assignedTo}
                  status={task.status}
                  dueDate={task.dueDate}
                  taskCategory={task.taskCategory}
                />
              </Grid>
            ))}
          </Grid>
        </Grid>

        
      </Grid>

      {/* SECOND PORTION */}
      {/* Top Row */}
      <br />
      {user === "mgr" && (
        <div>
          <Grid item xs={3}>
            <Box display="flex" alignItems="center" mb={2}>
              <Typography variant="h6" component="span">
                All Tasks
              </Typography>
            </Box>
          </Grid>

          <Grid container spacing={2}>
            <Grid item xs={10}>
              <Grid container spacing={2}>
                {tasks.map((task) => (
                  <Grid item xs={4} key={task.id}>
                    <TaskCard
                      tid={task.tid}
                      title={task.title}
                      priority={task.priority}
                      assignedTo={task.assignedTo}
                      status={task.status}
                      dueDate={task.dueDate}
                      taskCategory={task.taskCategory}
                    />
                  </Grid>
                ))}
              </Grid>
            </Grid>
          </Grid>
        </div>
      )}
      {/* Tasks Grid - Takes 70% of the width */}
      <Grid container spacing={2}>
        {/* Vertical Menu - Takes 30% of the width */}
        <Grid item xs={2}></Grid>
      </Grid>
    </div>
  );
};

export default Dashboard;

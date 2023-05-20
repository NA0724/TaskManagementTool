// import React, { useState } from 'react';
// import { Button, Form, Alert } from 'react-bootstrap';
// import { Link } from 'react-router-dom';

// const Dashboard: React.FC = () => {
//   return (
//     <Form>
//     <Alert variant='success'>Dashboard HERE (::)</Alert>
//       <Link to="/"><Button> Cancel </Button></Link>
//     </Form>
//   );
// };

// export default Dashboard;/

////////========================///////

// import React from "react";
// import { Typography, Grid, Paper } from "@mui/material";

// const Dashboard: React.FC = () => {
//   return (
//     <Grid container spacing={2}>
//       <Grid item xs={12}>
//         <Typography variant="h4">Dashboard</Typography>
//       </Grid>
//       <Grid item xs={6}>
//         <Paper style={{ height: 200, padding: 16 }}>
//           <Typography variant="h6">Card 1</Typography>
//         </Paper>
//       </Grid>
//       <Grid item xs={6}>
//         <Paper style={{ height: 200, padding: 16 }}>
//           <Typography variant="h6">Card 2</Typography>
//         </Paper>
//       </Grid>
//     </Grid>
//   );
// };

// export default Dashboard;

//V1:
// import React from "react";
// import {
//   Typography,
//   Grid,
//   Paper,
//   AppBar,
//   Toolbar,
//   IconButton,
//   InputBase,
//   Badge,
// } from "@mui/material";
// import { Search, AddCircleOutline, Notifications } from "@mui/icons-material";

// const Dashboard: React.FC = () => {
//   return (
//     <Grid container spacing={2}>
//       <Grid item xs={12}>
//         <AppBar position="static">
//           <Toolbar>
//             <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
//               Welcome, User!
//             </Typography>
//             <IconButton color="inherit" size="large">
//               <AddCircleOutline />
//             </IconButton>
//             <div>
//               <IconButton color="inherit" size="large">
//                 <Badge badgeContent={3} color="error">
//                   <Notifications />
//                 </Badge>
//               </IconButton>
//               <IconButton color="inherit" size="large">
//                 <Search />
//               </IconButton>
//               <InputBase
//                 placeholder="Search..."
//                 inputProps={{ "aria-label": "search" }}
//                 sx={{ ml: 1, color: "inherit", width: "200px" }}
//               />
//             </div>
//           </Toolbar>
//         </AppBar>
//       </Grid>
//       <Grid item xs={6}>
//         <Paper style={{ height: 200, padding: 16 }}>
//           <Typography variant="h6">Card 1</Typography>
//         </Paper>
//       </Grid>
//       <Grid item xs={6}>
//         <Paper style={{ height: 200, padding: 16 }}>
//           <Typography variant="h6">Card 2</Typography>
//         </Paper>
//       </Grid>
//     </Grid>
//   );
// };

// export default Dashboard;

//V2:
import React from "react";
import {
  Typography,
  Grid,
  Paper,
  AppBar,
  Toolbar,
  IconButton,
  InputBase,
  Badge,
  Drawer,
  List,
  ListItem,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import {
  Search,
  AddCircleOutline,
  Notifications,
  Dashboard as DashboardIcon,
  Assignment,
  Settings,
  Menu as MenuIcon,
} from "@mui/icons-material";
import "./Dashboard.css";

const Dashboard: React.FC = () => {
  const [isDrawerOpen, setIsDrawerOpen] = React.useState(false);

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
    { text: "Dashboard", icon: <DashboardIcon /> },
    { text: "Tasks", icon: <Assignment /> },
    { text: "Settings", icon: <Settings /> },
  ];

  return (
    <Grid container spacing={2}>
      <Grid item xs={12}>
        <AppBar position="static">
          <Toolbar>
            <IconButton
              color="inherit"
              size="large"
              onClick={toggleDrawer(true)}
            >
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              Welcome, User!
            </Typography>
            <IconButton color="inherit" size="large">
              <AddCircleOutline />
            </IconButton>
            <div>
              <IconButton color="inherit" size="large">
                <Badge badgeContent={3} color="error">
                  <Notifications />
                </Badge>
              </IconButton>
              <IconButton color="inherit" size="large">
                <Search />
              </IconButton>
              <InputBase
                placeholder="Search..."
                inputProps={{ "aria-label": "search" }}
                sx={{ ml: 1, color: "inherit", width: "200px" }}
              />
            </div>
          </Toolbar>
        </AppBar>
      </Grid>
      <Grid item xs={2}>
        <Drawer anchor="left" open={isDrawerOpen} onClose={toggleDrawer(false)}>
          <List>
            {drawerItems.map((item, index) => (
              <ListItem button key={index}>
                <ListItemIcon>{item.icon}</ListItemIcon>
                <ListItemText primary={item.text} />
              </ListItem>
            ))}
          </List>
        </Drawer>
      </Grid>
      <Grid item xs={10}>
        <Paper style={{ height: 200, padding: 16 }}>
          <Typography variant="h6">Card 1</Typography>
        </Paper>
      </Grid>
    </Grid>
  );
};

export default Dashboard;

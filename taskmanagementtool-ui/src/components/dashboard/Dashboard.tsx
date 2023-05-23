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
    <div className="dashboard-container">
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
            Welcome, User!
          </Typography>
          <IconButton color="inherit" size="large">
            <AddCircleOutline />
          </IconButton>
          <div className="search-bar">
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
      <Grid container spacing={2} className="content-container">
        <Grid item xs={3} className="drawer-container">
          <Drawer
            anchor="left"
            open={isDrawerOpen}
            onClose={toggleDrawer(false)}
          >
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
        {/* <Grid container spacing={2}>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 1</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 2</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 3</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 4</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 5</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 6</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 7</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 8</Typography>
            </Paper>
          </Grid>
          <Grid item xs={4}>
            <Paper style={{ height: 200, padding: 16 }}>
              <Typography variant="h6">Card 9</Typography>
            </Paper>
          </Grid>
        </Grid> */}

        <Grid container spacing={2}>
          <Grid item xs={8}>
            {/* Left Grid - Takes 70% of the width */}
            <Grid container spacing={2}>
              <Grid item xs={4}>
                {/* Content for Grid 1 */}
                <Paper style={{ height: 200 }}>Content for Grid 1</Paper>
              </Grid>
              <Grid item xs={4}>
                {/* Content for Grid 2 */}
                <Paper style={{ height: 200 }}>Content for Grid 2</Paper>
              </Grid>
              <Grid item xs={4}>
                {/* Content for Grid 3 */}
                <Paper style={{ height: 200 }}>Content for Grid 3</Paper>
              </Grid>
              <Grid item xs={4}>
                {/* Content for Grid 4 */}
                <Paper style={{ height: 200 }}>Content for Grid 4</Paper>
              </Grid>
              <Grid item xs={4}>
                {/* Content for Grid 5 */}
                <Paper style={{ height: 200 }}>Content for Grid 5</Paper>
              </Grid>
              <Grid item xs={4}>
                {/* Content for Grid 6 */}
                <Paper style={{ height: 200 }}>Content for Grid 6</Paper>
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={4}>
            {/* Vertical Menu - Takes 30% of the width */}
            <List component="nav" aria-label="menu">
              <ListItem button>
                <ListItemText primary="Menu Item 1" />
              </ListItem>
              <ListItem button>
                <ListItemText primary="Menu Item 2" />
              </ListItem>
              <ListItem button>
                <ListItemText primary="Menu Item 3" />
              </ListItem>
            </List>
          </Grid>
        </Grid>
      </Grid>
    </div>
  );
};

export default Dashboard;

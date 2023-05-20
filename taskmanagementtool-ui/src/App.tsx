import * as React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Alert, Container, Row, Col, Button } from "react-bootstrap";

import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Dashboard from "./components/dashboard/Dashboard";
import PrivateRoute from "./components/PrivateRoute";

const App: React.FC = () => {
  // const [isAuthenticated, setIsAuthenticated] = useState(false);

  const handleLoginSubmit = (email: string, password: string) => {
    console.log("@>@ Logging in with email", email, "and password", password);
  };

  return (
    <div className="App">
      <header className="App-header">
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/dashboard" element={<Dashboard />} />
          </Routes>
        </BrowserRouter>
      </header>
    </div>
  );
};

export default App;

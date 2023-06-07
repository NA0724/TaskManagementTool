/*import React from "react";
import { Button, Form, Alert } from "react-bootstrap";
import { Link } from "react-router-dom";
import MyDatePicker from "./MyDatePicker";
import './style.css';

const Home: React.FC = () => {
  return (
    <div className="App">
      <header className="App-header">
        <Alert variant="success">Task Management Tool</Alert>
        <Link to="/login">
          <Button> Login </Button>
        </Link>
        <Link to="/register">
          <Button> Register </Button>
        </Link>
      </header>
    </div>
  );
};
export default Home;


import React from "react";
import { Button, Form, Alert } from "react-bootstrap";
import { Link } from "react-router-dom";
import MyDatePicker from "./MyDatePicker";
import './style.css';

const Home: React.FC = () => {
  return (
   <div className="container-fluid">
        <nav className="navbar">
          <div className="navbar-brand">
            Task Management Tool
          </div>
          <div className="navbar-links">
            <Link to="/login">
              <Button variant="outline-primary">Login</Button>
            </Link>
            <Link to="/contact">
              <Button variant="outline-primary">Contact Us</Button>
            </Link>
          </div>
        </nav>
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-md-12">
              <Alert variant="success">Register Form</Alert>
              <Form>
                {/* Add form fields here }
                <Form.Group controlId="formFirstName">
                  <Form.Label>First Name</Form.Label>
                  <Form.Control type="text" placeholder="Enter your first name" />
                </Form.Group>
                {/* Add other form fields }
                <Button variant="primary" type="submit">Register</Button>
              </Form>
            </div>
          </div>
        </div>
        </div>
   
  );
};

export default Home;
*/

import React from "react";
import { Button, Form, Alert } from "react-bootstrap";
import { Link } from "react-router-dom";

const Home: React.FC = () => {
  return (
    
        <div className="container">
          <div className="row">
            <div className="col-md-6">
              <img src="/path/to/image.jpg" alt="Image" className="image" />
            </div>
            <div className="col-md-6">
              <Alert variant="success">Register Form</Alert>
              <Form className="form">
                {/* Add form fields here */}
                <Form.Group controlId="formFirstName">
                  <Form.Label>First Name</Form.Label>
                  <Form.Control type="text" placeholder="Enter your first name" />
                </Form.Group>
                {/* Add other form fields */}
                <Button variant="primary" type="submit">Register</Button>
                <Link to="/login">
              <Button variant="outline-primary">Login</Button>
            </Link>
              </Form>
            </div>
          </div>
        </div>
  );
};

export default Home;

import React, { useEffect, useState } from 'react';
import { Button, Form, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';


const Dashboard: React.FC = () => {


const [username, setUsername] = useState('');
  useEffect(() => {
    const sessionValue = sessionStorage.getItem('username');
    if (sessionValue) {
      setUsername(sessionValue);
    }
  }, []);
  const dash = async (dashboardData:FormData) => {
    try {
      
      if (username) {
        const headers = {
          "Content-Type": "application/json",
          "X-Session-User": username,
        };
      
      const response = await fetch("http://localhost:8080/api/v1/home/dashboard", {
        method: "GET",
        headers:  headers,
        body: JSON.stringify(dashboardData),
      });
      if (response.ok) {
        const data = await response.json();
        return { success:true, data };
      } else {
        const error = await response.text();
        return { success: false, error };
      }
    }
    } catch (error) {
      throw new Error("error.message");
    }
  };

  return (
    <div><h1>Welcome {username}</h1>
    <Form>
    <Alert variant='success'>Dashboard HERE (::)</Alert>
      <Link to="/"><Button> Cancel </Button></Link>
    </Form>
    </div>
  );
};

export default Dashboard;

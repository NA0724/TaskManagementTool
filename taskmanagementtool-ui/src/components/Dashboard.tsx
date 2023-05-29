import React, { useState } from 'react';
import { Button, Form, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';


const Dashboard: React.FC = () => {
  const auth = localStorage.getItem('auth');
  if (!auth) {
    throw new Error('Authorization token is missing');
  }
  const headers = new Headers({
    'Content-Type': 'application/json',
    'Authorization': auth
  });
  const dash = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/home/dashboard", {
        method: "GET",
        headers: headers
      });
      if (response.ok) {
        const data = await response.json();
        return { success:true, data };
      } else {
        const error = await response.text();
        return { success: false, error };
      }
    } catch (error) {
      throw new Error("error.message");
    }
  };

  return (
    <Form>
    <Alert variant='success'>Dashboard HERE (::)</Alert>
      <Link to="/"><Button> Cancel </Button></Link>
    </Form>
  );
};

export default Dashboard;

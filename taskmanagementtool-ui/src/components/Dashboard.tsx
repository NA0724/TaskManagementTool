import React, { useState } from 'react';
import { Button, Form, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';


const Dashboard: React.FC = () => {
  return (
    <Form>
    <Alert variant='success'>Dashboard HERE (::)</Alert>
      <Link to="/"><Button> Cancel </Button></Link>
    </Form>
  );
};

export default Dashboard;

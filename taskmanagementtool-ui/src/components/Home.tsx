import React from 'react';
import { Button, Form, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
    return (
        <div className='App'>
            <header className='App-header'>
                <Alert variant='success'>Task Management Tool</Alert>
                    <Link to="/login">
                        <Button> Login </Button>
                    </Link>
                    <Link to="/register">
                        <Button> Register </Button>
                    </Link>
                    <Link to="/dashboard">
                        <Button> Dashboard </Button>
                    </Link>
            </header>
        </div>
    );
}
export default Home
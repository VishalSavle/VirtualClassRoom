import React, { useState } from 'react';
import { loginUser } from '../services/UserService';
import './LoginComponent.css';

const LoginComponent = () => {
    const [credentials, setCredentials] = useState({
        email: '',
        password: '',
    });

    const handleChange = (e) => {
        setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await loginUser(credentials);
            localStorage.setItem('token', response.data.token); // Save JWT token
            alert('Login successful!');
        } catch (error) {
            alert('Login failed!');
        }
    };

    return (
        <div className="container">
            <h2>Login</h2>
            <form className="login-form" onSubmit={handleSubmit}>
                <input 
                    type="email" 
                    name="email" 
                    placeholder="Email" 
                    value={credentials.email} 
                    onChange={handleChange} 
                />
                <input 
                    type="password" 
                    name="password" 
                    placeholder="Password" 
                    value={credentials.password} 
                    onChange={handleChange} 
                />
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default LoginComponent;

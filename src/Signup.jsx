import React, { useState } from 'react';
import Header from './Header.jsx';
import Footer from './Footer.jsx';
import styles from './Signup.module.css';
import { useNavigate } from 'react-router-dom';

function Signup() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [role, setRole] = useState('User'); // Default role is 'User'
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // Handle input changes
    const handleNameChange = (e) => setName(e.target.value);
    const handleEmailChange = (e) => setEmail(e.target.value);
    const handlePhoneChange = (e) => setPhone(e.target.value);
    const handlePasswordChange = (e) => setPassword(e.target.value);
    const handleConfirmPasswordChange = (e) => setConfirmPassword(e.target.value);
    const handleRoleChange = (e) => setRole(e.target.value);

    // Handle form submission
    const handleSignup = (e) => {
        e.preventDefault();

        // Validate password and confirm password
        if (password !== confirmPassword) {
            setError('Passwords do not match');
            return;
        }

        const signupData = {
            name,
            email,
            phone,
            password,
            role
        };

        fetch('http://localhost:8080/api/signup', {  // Replace with actual backend signup endpoint
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(signupData),
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Redirect to login page or show success message
                console.log('Signup successful:', data);
                navigate('/login');  // Redirect to login page after successful signup
            } else {
                // Show error message
                setError('Signup failed. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error during signup:', error);
            setError('An error occurred during signup. Please try again later.');
        });
    };

    return (
        <div className={styles.container}>
            <Header />
            <div className={styles.LoginCard}>
                <label>Name</label><br />
                <input
                    type="text"
                    id="name"
                    placeholder="Full Name"
                    value={name}
                    onChange={handleNameChange}
                /><br />

                <label>Email</label><br />
                <input
                    type="text"
                    id="InputEmail"
                    placeholder="Email"
                    value={email}
                    onChange={handleEmailChange}
                /><br />

                <label>Phone Number</label><br />
                <input
                    type="text"
                    id="phonenumber"
                    placeholder="Phone Number"
                    value={phone}
                    onChange={handlePhoneChange}
                /><br />

                <label>Password</label><br />
                <input
                    type="password"
                    id="InputPass"
                    placeholder="Password"
                    value={password}
                    onChange={handlePasswordChange}
                /><br />

                <label>Confirm Password</label><br />
                <input
                    type="password"
                    id="ConfirmPass"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={handleConfirmPasswordChange}
                /><br />

                <label>Role</label>
                <select value={role} onChange={handleRoleChange}>
                    <option value="Agent">Agent</option>
                    <option value="User">User</option>
                </select><br />

                {error && <p style={{ color: 'red' }}>{error}</p>} {/* Show error message if any */}

                <button onClick={handleSignup}>Sign up</button>
            </div>
            <Footer />
        </div>
    );
}

export default Signup;

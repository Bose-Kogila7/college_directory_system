// src/components/Profile.js
import React, { useEffect, useState } from 'react';
import axiosInstance from '../AuthComponent/axiosConfig';
import { useNavigate } from 'react-router-dom';
import './Profile.css';

const Profile = () => {
    const [user, setUser] = useState(null);
    const [error, setError] = useState('');
    const [showConfirmation, setShowConfirmation] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await axiosInstance.get('/api/users/profile');
                setUser(response.data);
            } catch (error) {
                console.error('Error fetching user data:', error);
                setError('Failed to fetch user data. Please try again later.');
            }
        };

        fetchUserData();
    }, []);

    const handleDeleteProfile = async () => {
        try {
            await axiosInstance.delete(`/api/users/deleteUser/${user.id}`);
            setShowConfirmation(false);
            alert('Profile deleted successfully.');
            navigate('/');
        } catch (error) {
            console.error('Error deleting profile:', error);
            setError('Failed to delete profile. Please try again later.');
        }
    };

    const handleViewCourses = () => {
        navigate('/courses');
    };

    const handleViewDepartments = () => {
        navigate('/departments');
    };

    if (error) {
        return <p className="error-message">{error}</p>;
    }

    if (!user) {
        return <p>Loading...</p>;
    }

    return (
        <div className="profile-container">
            <h2>User Profile</h2>
            <div className="profile-info">
                <p><strong>Name:</strong> {user.name}</p>
                <p><strong>Email:</strong> {user.email}</p>
                <p><strong>Role:</strong> {user.role}</p>
            </div>
            <div className="profile-links">
                <button className="profile-button" onClick={handleViewCourses}>View Courses</button>
                <button className="profile-button" onClick={handleViewDepartments}>View Departments</button>
                <button className="profile-button delete-button" onClick={() => setShowConfirmation(true)}>Delete Profile</button>
            </div>
            {showConfirmation && (
                <div className="modal">
                    <div className="modal-content">
                        <p>Are you sure you want to delete your profile?</p>
                        <button onClick={handleDeleteProfile}>Yes</button>
                        <button onClick={() => setShowConfirmation(false)}>No</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Profile;
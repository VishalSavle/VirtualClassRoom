import React from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css'; // Optional: Import a global CSS file
import ClassroomComponent from './Components/Pages/Classroom/class';
import CommentComponent from './Components/Pages/Comment/CommentComponent';
import EnrollmentComponent from './Components/Pages/Enrollment/EnrollmentComponent';
import LectureComponent from './Components/Pages/Lecture/LectureComponent';
import LoginComponent from './Components/Pages/Login/LoginComponent';
import RegistrationComponent from './Components/Pages/Registration/RegistrationComponent';
import SessionComponent from './Components/Pages/Sessions/SessionComponent';
import UnitComponent from './Components/Pages/Unit/UnitComponent';

const App = () => {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/" element={<LoginComponent />} />
                    <Route path="/register" element={<RegistrationComponent />} />
                    <Route path="/classrooms" element={<ClassroomComponent />} />
                    <Route path="/units" element={<UnitComponent />} />
                    <Route path="/sessions" element={<SessionComponent />} />
                    <Route path="/lectures" element={<LectureComponent />} />
                    <Route path="/enrollments" element={<EnrollmentComponent />} />
                    <Route path="/comments" element={<CommentComponent />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;

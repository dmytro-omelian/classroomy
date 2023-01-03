import { React, useState, useEffect } from 'react';
import CoursePreview from '../course/preview/CoursePreview';
import Search from '../search/Search';
import Spinner from '../spinner/Spinner';
import './index.css';

export default function Main() {
    const [input, setInput] = useState('');
    const [courses, setCourses] = useState([]);
    const [loading, setLoading] = useState(false);

    // TODO should have refresh button
    // TODO auth page to do firstly

    const loadCourses = () => {
        fetch('http://localhost:8080/courses')
            .then((response) => response.json())
            .then((data) => {
                setCourses(data);
                setLoading(false);
            });
    };

    useEffect(() => {
        setLoading(true);
        loadCourses();
    }, []);

    if (loading) {
        return <Spinner />;
    }

    // eslint-disable-next-line
    const filterCourses = () => {
        console.log(input);
        if (input === '') {
            loadCourses();
            return;
        }
        setCourses(courses.filter((course) => course.name.includes(input)));
    };

    return (
        <div>
            <Search
                input={input}
                setInput={setInput}
                filterCourses={filterCourses}
            />
            <div className="container">
                {courses.map((course) => {
                    return <CoursePreview key={course.id} course={course} />;
                })}
            </div>
        </div>
    );
}

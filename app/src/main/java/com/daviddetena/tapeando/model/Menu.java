package com.daviddetena.tapeando.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static Menu sInstance;          // Singleton with list of courses
    private List<Course> mCourses;          // list of courses

    /**
     * Static method to get the singleton of Menu
     * @return
     */
    public static Menu getInstance(){
        if(sInstance == null){
            sInstance = new Menu();
        }
        return sInstance;
    }

    /**
     * Private constructor to hold the singleton. Init the list of courses
     */
    private Menu(){
        mCourses = new ArrayList<>();
    }

    /**
     * Get all the courses
     * @return
     */
    public List<Course> getCourses(){
        return mCourses;
    }

    /**
     * Get the course with the given id
     * @param id
     * @return
     */
    public Course getCourse(int id){
        for(Course course:mCourses){
            if(course.getId()==id){
                return course;
            }
        }
        return null;
    }

    /**
     * Reset the list to include no courses
     */
    public void clearCourses(){
        mCourses = null;
    }


    /**
     * Add a new course to the list
     * @param course
     */
    public void addCourse(Course course){
        mCourses.add(course);
    }
}

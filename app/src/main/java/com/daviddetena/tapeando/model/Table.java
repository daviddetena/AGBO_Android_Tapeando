package com.daviddetena.tapeando.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    // A table has a number and a list of courses
    private int mTableNumber;
    private List<Course> mCourses;

    /**
     * Full constructor
     * @param tableNumber
     * @param courses
     */
    public Table(int tableNumber, List<Course> courses){
        mTableNumber = tableNumber;
        mCourses = courses;
    }

    /**
     * Constructor only with the table number
     * @param tableNumber
     */
    public Table(int tableNumber) {
        mTableNumber = tableNumber;
        mCourses = new ArrayList<>();
    }

    public int getTableNumber() {
        return mTableNumber;
    }

    public void setTableNumber(int tableNumber) {
        mTableNumber = tableNumber;
    }

    public List<Course> getCourses() {
        return mCourses;
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
    }

    @Override
    public String toString() {
        return String.valueOf(getTableNumber()) + ": " + String.format("%.2f€", getBill());
    }


    /**
     * Add a new Course to the table, w/o notes
     * @param course
     */
    public void addCourse(Course course){
        course.setId(mCourses.size() + 1);
        mCourses.add(course);
    }


    /**
     * Add a new Course to the table, with notes
     * @param course
     * @param notes
     */
    public void addCourse(Course course, String notes){
        course.setId(mCourses.size() + 1);
        course.setNotes(notes);
        mCourses.add(course);
    }

    //TODO: include updateCourse() and removeCourse() methods


    /**
     * Remove all the courses for the table
     */
    public void cleanTable() {
        mCourses = new ArrayList<>();
    }

    /**
     * Calculate the table bill
     * @return
     */
    public float getBill() {
        float bill = 0.0f;
        for(Course course:mCourses){
            bill+=course.getPrice();
        }
        return bill;
    }


}
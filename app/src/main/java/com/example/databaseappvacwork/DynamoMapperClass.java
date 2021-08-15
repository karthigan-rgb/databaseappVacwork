package com.example.databaseappvacwork;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "Students")
public class DynamoMapperClass {
    String StudentID;
    String Name;
    @DynamoDBHashKey(attributeName = "StudentID")
    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }
    @DynamoDBHashKey(attributeName = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}


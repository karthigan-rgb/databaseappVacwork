package com.example.databaseappvacwork;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class ManagerClass {
    public static AmazonDynamoDBClient dynamoDBClient = null;
    public static DynamoDBMapper dynamoDBMapper = null;
    public CognitoCachingCredentialsProvider getcredentials(Context context) {
        // Initialize the Amazon Cognito credentials provide
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                "us-east-2:1f85ca69-8faf-4e69-b767-2267da583d09", // Identity pool ID
                Regions.US_EAST_2 // Region
        );
        return credentialsProvider;
    }

    public DynamoDBMapper intiDynamoClient(CognitoCachingCredentialsProvider credentialsProvider){
        if(dynamoDBClient==null){
            dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
            dynamoDBClient.setRegion(Region.getRegion(Regions.US_EAST_2));
            dynamoDBMapper = new DynamoDBMapper(dynamoDBClient);
        }
        return dynamoDBMapper;
    }
}

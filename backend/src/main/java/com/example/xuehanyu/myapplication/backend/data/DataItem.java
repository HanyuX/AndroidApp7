package com.example.xuehanyu.myapplication.backend.data;

/**
 * Created by dnalwqer on 5/16/16.
 */
public class DataItem {
    public static final String ENTRY_PARENT_ENTITY_NAME = "DataItemParent";
    public static final String ENTRY_PARENT_KEY_NAME = ENTRY_PARENT_ENTITY_NAME;

    public static final String ENTRY_ENTITY_NAME = "DataItem";
    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_ACTIVITYTYPE = "activitytype";
    public static final String FIELD_NAME_INPUTTYPE = "inputtype";
    public static final String FIELD_NAME_DATETIME = "datetime";
    public static final String FIELD_NAME_DURATION = "duration";
    public static final String FIELD_NAME_DISTANCE = "distance";
    public static final String FIELD_NAME_SPEED = "avgSpeed";
    public static final String FIELD_NAME_CALORIES = "calories";
    public static final String FIELD_NAME_CLIMB = "climb";
    public static final String FIELD_NAME_HEARTRATE = "heartrate";
    public static final String FIELD_NAME_COMMENT = "comment";
    public static final String KEY_NAME = FIELD_NAME_ID;

    public String Id;
    public String InputType;
    public String ActivityType;
    public String DateTime;
    public String Duration;
    public String Distance;
    public String AvgSpeed;
    public String Calories;
    public String Climb;
    public String HeartRate;
    public String Comment;

    public DataItem(String Id, String InputType, String ActivityType, String DateTime, String Duration, String Distance, String AvgSpeed, String
                    Calories, String Climb, String HeartRate, String Comment) {
        this.Id = Id;
        this.InputType = InputType;
        this.ActivityType = ActivityType;
        this.DateTime = DateTime;
        this.Duration = Duration;
        this.Distance = Distance;
        this.Calories = Calories;
        this.AvgSpeed = AvgSpeed;
        this.Climb = Climb;
        this.HeartRate = HeartRate;
        this.Comment = Comment;
    }
}

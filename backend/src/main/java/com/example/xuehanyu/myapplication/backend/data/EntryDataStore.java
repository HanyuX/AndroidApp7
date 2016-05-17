package com.example.xuehanyu.myapplication.backend.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by dnalwqer on 5/16/16.
 */
public class EntryDataStore {
    private static final Logger mLogger = Logger
            .getLogger(EntryDataStore.class.getName());
    private static final DatastoreService mDatastore = DatastoreServiceFactory
            .getDatastoreService();

    private static Key getParentKey() {
        return KeyFactory.createKey(DataItem.ENTRY_PARENT_ENTITY_NAME,
                DataItem.ENTRY_PARENT_KEY_NAME);
    }

    private static void createParentEntity() {
        Entity entity = new Entity(getParentKey());

        mDatastore.put(entity);
    }

    public static boolean add(DataItem item) {
        Key parentKey = getParentKey();
        try {
            mDatastore.get(parentKey);
        } catch (Exception ex) {
            createParentEntity();
        }

        Entity entity = new Entity(DataItem.ENTRY_ENTITY_NAME, item.Id, parentKey);
        entity.setProperty(DataItem.FIELD_NAME_ID, item.Id);
        entity.setProperty(DataItem.FIELD_NAME_INPUTTYPE, item.InputType);
        entity.setProperty(DataItem.FIELD_NAME_ACTIVITYTYPE, item.ActivityType);
        entity.setProperty(DataItem.FIELD_NAME_DATETIME, item.DateTime);
        entity.setProperty(DataItem.FIELD_NAME_DURATION, item.Duration);
        entity.setProperty(DataItem.FIELD_NAME_DISTANCE, item.Distance);
        entity.setProperty(DataItem.FIELD_NAME_SPEED, item.AvgSpeed);
        entity.setProperty(DataItem.FIELD_NAME_CLIMB, item.Climb);
        entity.setProperty(DataItem.FIELD_NAME_CALORIES, item.Calories);
        entity.setProperty(DataItem.FIELD_NAME_HEARTRATE, item.HeartRate);
        entity.setProperty(DataItem.FIELD_NAME_COMMENT, item.Comment);

        mDatastore.put(entity);

        return true;
    }

    public static boolean delete(String id) {
        // you can also use name to get key, then use the key to delete the
        // entity from datastore directly
        // because name is also the entity's key

        // query
        Query.Filter filter = new Query.FilterPredicate(DataItem.FIELD_NAME_ID,
                Query.FilterOperator.EQUAL, id);

        Query query = new Query(DataItem.ENTRY_ENTITY_NAME);
        query.setFilter(filter);

        // Use PreparedQuery interface to retrieve results
        PreparedQuery pq = mDatastore.prepare(query);

        Entity result = pq.asSingleEntity();
        boolean ret = false;
        if (result != null) {
            // delete
            mDatastore.delete(result.getKey());
            ret = true;
        }

        return ret;
    }

    public static ArrayList<DataItem> query() {
        ArrayList<DataItem> resultList = new ArrayList<DataItem>();
        Query query = new Query(DataItem.ENTRY_ENTITY_NAME);
        // get every record from datastore, no filter
        query.setFilter(null);
        // set query's ancestor to get strong consistency
        query.setAncestor(getParentKey());

        PreparedQuery pq = mDatastore.prepare(query);

        for (Entity entity : pq.asIterable()) {
            DataItem item = getContactFromEntity(entity);
            if (item != null) {
                resultList.add(item);
            }
        }
        return resultList;
    }

    private static DataItem getContactFromEntity(Entity entity) {
        if (entity == null) {
            return null;
        }

        return new DataItem(
                (String) entity.getProperty(DataItem.FIELD_NAME_ID),
                (String) entity.getProperty(DataItem.FIELD_NAME_INPUTTYPE),
                (String) entity.getProperty(DataItem.FIELD_NAME_ACTIVITYTYPE),
                (String) entity.getProperty(DataItem.FIELD_NAME_DATETIME),
                (String) entity.getProperty(DataItem.FIELD_NAME_DURATION),
                (String) entity.getProperty(DataItem.FIELD_NAME_DISTANCE),
                (String) entity.getProperty(DataItem.FIELD_NAME_SPEED),
                (String) entity.getProperty(DataItem.FIELD_NAME_CALORIES),
                (String) entity.getProperty(DataItem.FIELD_NAME_CLIMB),
                (String) entity.getProperty(DataItem.FIELD_NAME_HEARTRATE),
                (String) entity.getProperty(DataItem.FIELD_NAME_COMMENT));
    }
}

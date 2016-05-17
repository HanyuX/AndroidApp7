package com.example.xuehanyu.myapplication.backend;

import com.example.xuehanyu.myapplication.backend.data.DataItem;
import com.example.xuehanyu.myapplication.backend.data.EntryDataStore;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dnalwqer on 5/16/16.
 * Add item into dataset
 */
public class AddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String id = req.getParameter("id");
        String inputtype = req.getParameter("inputtype");
        String activitytype = req.getParameter("activitytype");
        String datetime = req.getParameter("datetime");
        String duration = req.getParameter("duration");
        String distance = req.getParameter("distance");
        String avgspeed = req.getParameter("avgspeed");
        String calories = req.getParameter("calories");
        String climb = req.getParameter("climb");
        String heartrate = req.getParameter("heartrate");
        String comment = req.getParameter("comment");

        //genarate dataItem
        DataItem item = new DataItem(id, inputtype, activitytype, datetime, duration, distance, avgspeed, calories, climb, heartrate, comment);
        EntryDataStore.add(item);

        ArrayList<DataItem> result = EntryDataStore.query();
        req.setAttribute("result", result);
        getServletContext().getRequestDispatcher("/query_result.jsp").forward(
                req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}

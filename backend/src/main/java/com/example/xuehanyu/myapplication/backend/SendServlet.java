package com.example.xuehanyu.myapplication.backend;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.xuehanyu.myapplication.backend.OfyService.ofy;

/**
 * Created by dnalwqer on 5/16/16.
 */
public class SendServlet extends HttpServlet {

    /** Api Keys can be obtained from the google cloud console */
    private static final String API_KEY = System.getProperty("gcm.api.key");
    private static final String message = "delete";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String id = req.getParameter("ID");

        Sender sender = new Sender(API_KEY);
        Message msg = new Message.Builder().addData("message", message).addData("ID", id).build();
        List<RegistrationRecord> records = ofy().load().type(RegistrationRecord.class).limit(10).list();
        for(RegistrationRecord record : records) {
            sender.send(msg, record.getRegId(), 5);
        }
        resp.sendRedirect("/query.do");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}

package com.example.xuehanyu.myapplication.backend;

import com.example.xuehanyu.myapplication.backend.data.EntryDataStore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dnalwqer on 5/16/16.
 * Delete one item from dataset
 * Inform mobile to delete the same item
 */
public class DeleteServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String id = req.getParameter("ID"); //ID of the item to delete
        EntryDataStore.delete(id);
        getServletContext().getRequestDispatcher("/send.do").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        doGet(req, resp);
    }
}

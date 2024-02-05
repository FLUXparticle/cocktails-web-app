package com.example.cocktails.web;

import com.example.cocktails.logic.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet("/cocktails")
public class CocktailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("plain/text");
        try {
            for (Map.Entry<Integer, String> entry : CocktailApp.getCocktails().entrySet()) {
                out.println("Cocktail ID: " + entry.getKey() + ", Name: " + entry.getValue());
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}

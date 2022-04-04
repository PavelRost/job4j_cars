package ru.job4j.controller;

import ru.job4j.model.Advertisement;
import ru.job4j.model.Car;
import ru.job4j.model.User;
import ru.job4j.repository.AdsRepository;
import ru.job4j.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", CarService.instOf().findAllCars());
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        String mark = req.getParameter("mark");
        String body = req.getParameter("body");
        User currentUser = (User) req.getSession().getAttribute("user");
        int idCar = Integer.parseInt(req.getParameter("car"));
        Car car = CarService.instOf().findCarById(idCar);
        Advertisement advertisement = Advertisement.of(
                description,
                mark,
                body,
                car,
                currentUser
        );
        AdsRepository.instOf().addAd(advertisement);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}

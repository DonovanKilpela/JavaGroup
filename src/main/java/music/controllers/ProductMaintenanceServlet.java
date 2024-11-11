package music.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import music.business.Product;
import music.data.ProductDB;

import java.util.List;

@WebServlet("/productMaint")
public class ProductMaintenanceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewProducts"; 
        }

        String url = "/products.jsp";

        if (action.equals("addProduct")) {
            // Read user input
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));

            // Create new product and add it to the database
            Product product = new Product();
            product.setCode(code);
            product.setDescription(description);
            product.setPrice(price);

            ProductDB.insertProduct(product);
            url = "/products.jsp";
        } else if (action.equals("updateProduct")) {
            // Read user input
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));

            // Update existing product
            Product product = new Product();
            product.setCode(code);
            product.setDescription(description);
            product.setPrice(price);

            ProductDB.updateProduct(product);
            url = "/products.jsp";
        } else if (action.equals("deleteProduct")) {
            // Read user input
            String code = request.getParameter("code");

            // Delete product
            ProductDB.deleteProduct(code);
            url = "/products.jsp";
        }

        // Forward to the appropriate page
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
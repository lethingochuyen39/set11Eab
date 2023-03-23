package org.aptech.controller;

import org.aptech.entities.Account;
import org.aptech.entities.SaveTransaction;
import org.aptech.services.EmployeeService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/bank")
public class BankController extends HttpServlet {

    @EJB
    EmployeeService<Account> accountService;

    @EJB
    EmployeeService<SaveTransaction> saveTransactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "Login" : req.getParameter("action");

        if(action.equalsIgnoreCase("Login")){
            req.getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("Login")) {
            accountService.setType(Account.class);
            String userName = req.getParameter("txtName");
            String password = req.getParameter("txtPass");

            Account account = accountService.getEntityById(userName);
            if (account!= null && account.getPinCode().equals(password) ) {
                req.getSession().setAttribute("account", account);
                req.getServletContext().getRequestDispatcher("/transfer.jsp").forward(req, resp);
            }
            else {
                req.setAttribute("error", "Wrong username or password");
                req.getServletContext().getRequestDispatcher("/login.jsp").include(req, resp);
            }

        } else if (action.equalsIgnoreCase("transfer")) {

             accountService.setType(Account.class);
             String accontSource = req.getParameter("txtaccount");
            String accontTarget = req.getParameter("txtaccountto");
            Double amount = Double.parseDouble(req.getParameter("txtamount"));
            String comment = req.getParameter("txtcomment");


            Account source = accountService.getEntityById(accontSource);
             Account target = accountService.getEntityById(accontTarget);
            if(source == null || target == null || amount <= 0){
                req.setAttribute("error", "Transfer fail !!!");
                req.getServletContext().getRequestDispatcher("/transfer.jsp").include(req, resp);
            }
            if(source.getBalance() <= amount){
                req.setAttribute("error", "Your balance is not enough to make the transaction. Transfer fail !!!");
                req.getServletContext().getRequestDispatcher("/transfer.jsp").include(req, resp);

            }else {
                source.setBalance(source.getBalance() - amount);
                target.setBalance(target.getBalance() + amount);

                accountService.updateEntity(source);
                accountService.updateEntity(target);

                SaveTransaction saveTransaction = new SaveTransaction();

                saveTransaction.setAccountNo(accontSource);
                saveTransaction.setAmount(amount);
                saveTransaction.setComment(comment);
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                saveTransaction.setTranDate(simpleDateFormat.format(date));

                if(saveTransactionService.addEntity(saveTransaction)){

                    req.setAttribute("msg", "Transfer success");
                    req.getSession().setAttribute("account", source);
                    req.getServletContext().getRequestDispatcher("/transfer.jsp").include(req, resp);
                }else {
                    req.setAttribute("error", "Transfer fail");
                    req.getServletContext().getRequestDispatcher("/transfer.jsp").include(req, resp);
                }
            }

        }
    }
}

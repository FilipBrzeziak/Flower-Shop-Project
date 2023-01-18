package com.example.flowershopproject.controller;

import com.example.flowershopproject.dao.AccountDAO;
import com.example.flowershopproject.dao.OrderDAO;
import com.example.flowershopproject.dao.ProductDAO;
import com.example.flowershopproject.entity.Account;
import com.example.flowershopproject.entity.Product;
import com.example.flowershopproject.form.ProductForm;
import com.example.flowershopproject.model.AccountInfo;
import com.example.flowershopproject.model.CartInfo;
import com.example.flowershopproject.model.OrderDetailInfo;
import com.example.flowershopproject.model.OrderInfo;
import com.example.flowershopproject.pagination.PaginationResult;
import com.example.flowershopproject.service.UserDetailsServiceImpl;
import com.example.flowershopproject.utils.Utils;
import com.example.flowershopproject.validator.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Transactional
public class UserController {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ProductFormValidator productFormValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == ProductForm.class) {
            dataBinder.setValidator(productFormValidator);
        }
    }

    @RequestMapping(value = { "/shop/login" }, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = { "/shop/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {
        UserDetails simpleDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDAO.findAccount(simpleDetails.getUsername());
        AccountInfo accountInfo = new AccountInfo(account.getUserName(), account.isActive(), account.getEmail(),
                account.getPhone(), account.getFirstName(), account.getLastName(), account.getUserAddress(),
                account.getUserCity());
        model.addAttribute("accountInfo", accountInfo);
        return "accountInfo";
    }

    @RequestMapping(value = { "/shop/editAccountDetails" }, method = {RequestMethod.GET})
    public String editAccountDetails(Model model) {
        UserDetails simpleDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountDAO.findAccount(simpleDetails.getUsername());
        AccountInfo accountInfo = new AccountInfo(account.getUserName(), account.isActive(), account.getEmail(),
                account.getPhone(), account.getFirstName(), account.getLastName(), account.getUserAddress(),
                account.getUserCity());
        model.addAttribute("accountInfo", accountInfo);
        return "editAccountDetails";
    }
    @RequestMapping(value = { "/shop/editAccountDetails" }, method = {RequestMethod.POST})
    public String editAccountDetails(Model model, @ModelAttribute("accountInfo") AccountInfo accountInfo ) {
        UserDetails simpleDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountDAO.updateAccount(simpleDetails.getUsername(),accountInfo.getEmail(),accountInfo.getPhoneNumber(),accountInfo.getAddress(),accountInfo.getName(),accountInfo.getLastName(),accountInfo.getCity());
        return "accountInfo";
    }

    @RequestMapping(value = { "/shop/userOrderList" }, method = RequestMethod.GET)
    public String userOrderList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;

        PaginationResult<OrderInfo> paginationResult = orderDAO.listOrderInfoForSingleUser(page, MAX_RESULT, MAX_NAVIGATION_PAGE, userDetails.getUsername());

        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }

    @RequestMapping(value = { "/shop/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;

        PaginationResult<OrderInfo> paginationResult = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }

    @RequestMapping(value = { "/shop/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductForm productForm = null;

        if (code != null && code.length() > 0) {
            Product product = productDAO.findProduct(code);
            if (product != null) {
                productForm = new ProductForm(product);
            }
        }
        if (productForm == null) {
            productForm = new ProductForm();
            productForm.setNewProduct(true);
        }
        model.addAttribute("productForm", productForm);
        return "product";
    }

    @RequestMapping(value = { "/shop/product" }, method = RequestMethod.POST)
    public String productSave(Model model, @ModelAttribute("productForm") @Validated ProductForm productForm,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "product";
        }
        try {
            productDAO.save(productForm);
        } catch (Exception e) {
            String message = e.getMessage();
            model.addAttribute("errorMessage", message);
            return "product";
        }
        return "redirect:/productList";
    }


    @RequestMapping(value = {"/shop/bouquetCreator"}, method = RequestMethod.GET)
    public String getbouquetCreator(HttpServletRequest request, Model model) {
        return "bouquetCreator";
    }

    @RequestMapping(value = {"/shop/bouquetCreator"}, method = RequestMethod.POST)
    public String createbouquetCreator(String flowerSelect, Model model) {
        System.out.print(flowerSelect);
       return "redirect:/shoppingCart";

    }

    @RequestMapping(value = { "/shop/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/shop/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);

        model.addAttribute("orderInfo", orderInfo);

        return "order";
    }

}

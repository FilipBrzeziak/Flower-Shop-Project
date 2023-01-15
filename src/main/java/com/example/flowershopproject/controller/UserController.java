package com.example.flowershopproject.controller;

import com.example.flowershopproject.dao.OrderDAO;
import com.example.flowershopproject.dao.ProductDAO;
import com.example.flowershopproject.entity.Product;
import com.example.flowershopproject.form.ProductForm;
import com.example.flowershopproject.model.AccountInfo;
import com.example.flowershopproject.model.OrderDetailInfo;
import com.example.flowershopproject.model.OrderInfo;
import com.example.flowershopproject.pagination.PaginationResult;
import com.example.flowershopproject.service.UserDetailsServiceImpl;
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

import java.util.List;

@Controller
@Transactional
public class UserController {

    @Autowired
    private OrderDAO orderDAO;

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
        UserDetails userToReturn = userDetailsService.loadUserByUsername(simpleDetails.getUsername());
        AccountInfo userDetails = new AccountInfo(userToReturn.getUsername(), userToReturn.isEnabled());
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
    }

    @RequestMapping(value = { "/shop/accountDetails" }, method = RequestMethod.GET)
    public String accountDetails(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userToReturn = userDetailsService.loadUserByUsername(userDetails.getUsername());
        AccountInfo accountInfo = new AccountInfo(userToReturn.getUsername(), userToReturn.isEnabled());
        model.addAttribute("accountInfo", accountInfo);
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

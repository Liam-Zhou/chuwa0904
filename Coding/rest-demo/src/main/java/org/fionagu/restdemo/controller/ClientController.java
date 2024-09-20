package org.fionagu.restdemo.controller;

import org.fionagu.restdemo.Client;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClientController {

    /*获取client payment*/
    @RequestMapping(value = "/clients/{clientID}/payments",method = RequestMethod.GET)
    @ResponseBody
    public List<Client> getClientPaymentType(@PathVariable Long clientID){
        //创建客户 模拟提取payment方式
        //Simulate fetching payment method by creating client object based on clientId from request
        List<Client> pt = Arrays.asList(new Client(clientID,"Beckey",18,"Paypal",LocalDate.now(),"Chino,CA"));
        return pt;
    }

    /*查找client 特定历史订单记录*/
    /*Test Url: http://localhost:8080/clients/2022-10-01/2022-10-24/orders */
    /*correct format in send request: YYYY-MM-DD*/
    @RequestMapping(value = "/clients/{startDate}/{endDate}/orders",method = RequestMethod.GET)
    @ResponseBody
    public List<Client> getHisOrderByDate(
            //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            //ensure the dates are parsed correctly from the URL:
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        List<Client> ordersByDate = Arrays.asList(
                new Client(33L,"cici",18,"Credit Card",LocalDate.of(2022,10,11),"pomona,CA"),
                new Client(44L,"gigi",18,"Apply Pay",LocalDate.of(2022,10,15),"pomona,CA")
        );
        return ordersByDate;
    }

    /*查找client的送货地址*/
    /*Test Url: http://localhost:8080/clients/55/address */
    @RequestMapping(value = "/clients/{clientID}/address",method = RequestMethod.GET)
    @ResponseBody
    public String getClientAddress(@PathVariable Long clientID){
        Client client = new Client(clientID,"kiki",18,"Paypal",LocalDate.now(),"pomona,CA");
        return "The clinet address is "+client.getAddress();
    }

    /*查找client default付款方式和地址*/
    /*Test Url: http://localhost:8080/clients/66/default-address-payment */

//    @RequestMapping(value = "/clients/{clientID}/default-address-payment",method = RequestMethod.GET)
//    @ResponseBody
//    public String getClientDefault(@PathVariable Long clientID){
//        Client client = new Client(clientID,"gigi",18,"Paypal",LocalDate.now(),"pomona,CA");
//        return "The clinet default address is "+client.getAddress()+" default payment method is "+client.getPayment();
//    }

    @RequestMapping(value = "/clients/{clientID}/default-address-payment",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getClientDefault(@PathVariable Long clientID){
        Client client = new Client(clientID,"gigi",18,"Paypal",LocalDate.now(),"pomona,CA");
        //create a respone map with defualt address and payment method
        Map<String,String> res = new HashMap<>();
        res.put("Deafult address ",client.getAddress());
        res.put("Default payment: ",client.getPayment());
        return res;
    }


}

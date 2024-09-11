package edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.controller;

import edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.model.Message;
import edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.model.User;
import edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.service.LoadBalancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private LoadBalancerService loadBalancerService;

    @GetMapping("/messages")
    public String listMessages(Model model) {
        List<Message> messages = loadBalancerService.getAllMessages();
        model.addAttribute("messages", messages);
        return "messages";
    }

    @PostMapping("/v1/messages/agregar")
    public String addMessage(@RequestParam("log") String log,
                             @RequestParam("message") String message) {

        loadBalancerService.addMessage(log, message);
        return "redirect:/messages"; // Redirige a la lista de mensajes
    }
}




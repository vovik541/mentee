package com.learn.mentee.controller;

import com.learn.mentee.entity.Event;
import com.learn.mentee.entity.Ticket;
import com.learn.mentee.entity.User;
import com.learn.mentee.entity.enumaration.TicketCategory;
import com.learn.mentee.service.EventService;
import com.learn.mentee.service.TicketService;
import com.learn.mentee.service.UserService;
import com.learn.mentee.util.exporter.impl.TicketPDFExporter;
import com.learn.mentee.util.exporter.impl.XMLToTicketExporter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private TicketService ticketService;

    @Resource
    private UserService userService;

    @Resource
    private EventService eventService;

    @Resource
    private TicketPDFExporter ticketPDFExporter;

    @Resource
    private XMLToTicketExporter xmlToTicketExporter;

    @GetMapping("/actions")
    public String getTicketActions() {
        return "ticket-actions";
    }

    @PostMapping("/book")
    public String bookTicket(@RequestParam long userId,
                             @RequestParam long eventId,
                             @RequestParam int place,
                             @RequestParam String ticketCategoryName,
                             Model model) {
        TicketCategory ticketCategory = TicketCategory.valueOf(ticketCategoryName.toUpperCase());
        model.addAttribute("ticket", ticketService.bookTicket(userId, eventId, place, ticketCategory));
        return "ticket";
    }

    @GetMapping("/getBookedTicketsByUser")
    public String getBookedTicketsByUser(@RequestParam long userId,
                                         @RequestParam int pageSize,
                                         @RequestParam int pageNum,
                                         Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("tickets", ticketService.getBookedTicketsByUser(user, pageSize, pageNum));
        return "tickets";
    }

    @GetMapping("/getBookedTicketsByEvent")
    public String getBookedTicketsByEvent(@RequestParam long eventId,
                                          @RequestParam int pageSize,
                                          @RequestParam int pageNum,
                                          Model model) {
        Event event = eventService.getEventById(eventId);
        model.addAttribute("tickets", ticketService.getBookedTicketsByEvent(event, pageSize, pageNum));
        return "tickets";
    }

    @PostMapping("/cancelTicket")
    public String cancelTicket(@RequestParam long ticketId) {
        ticketService.cancelTicket(ticketId);
        return "ticket-actions";
    }

    @GetMapping(headers = "accept=application/pdf")
    public void getPDFTicketsByUser(HttpServletResponse response,
                                    @RequestParam long userId,
                                    @RequestParam int pageSize,
                                    @RequestParam int pageNum) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=tickets_" + currentDateTime + ".pdf";

        response.setHeader(headerKey, headerValue);

        List<Ticket> tickets = ticketService.getBookedTicketsByUser(userService.getUserById(userId), pageSize, pageNum);

        ticketPDFExporter.export(tickets, response.getOutputStream());
    }

    //TODO make /preloadTickets mapping to parse list of tickets from xml

    @GetMapping("/preloadTicket")
    public String preloadTickets() {
        Ticket ticket = xmlToTicketExporter.exportTicketFromXml();
        ticketService.bookTicket(ticket.getUserId(), ticket.getEventId(), ticket.getPlace(), ticket.getTicketCategory());
        return "redirect:/ticket/getBookedTicketsByUser?userId="
                + ticket.getUserId()
                + "&pageSize=500&pageNum=1";
    }
}

package com.learn.mentee.controller;

import com.learn.mentee.entity.Event;
import com.learn.mentee.service.EventService;
import com.learn.mentee.util.dataparser.impl.DateParserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    @Resource
    private EventService eventService;

    @Resource
    private DateParserImpl dateParser;

    @GetMapping("/{id}")
    public String getEventById(Model model, @PathVariable long id) {
        model.addAttribute("event", eventService.getEventById(id));
        return "event";
    }

    @GetMapping("/title")
    public String getEventByTitle(Model model, @RequestParam String title,
                                  @RequestParam int pageSize,
                                  @RequestParam int pageNum){
        List<Event> events = eventService.getEventByTitle(title, pageSize, pageNum);
        model.addAttribute("events", events);
        return "events";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam long id,
                             @RequestParam String title,
                             @RequestParam String date,
                             Model model) {

        Date parsedDate = dateParser.parseDate(date);

        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setDate(parsedDate);

        model.addAttribute("event", eventService.createEvent(event));

        return "redirect:/event/" + id;
    }

    @GetMapping("/getByDate")
    public String getEventsForDay(@RequestParam("date") String date,
                                  @RequestParam("pageNum") int pageNum,
                                  @RequestParam("pageSize") int pageSize,
                                  Model model) {
        Date parsedDate = dateParser.parseDate(date);
        model.addAttribute("events", eventService.getEventsForDay(parsedDate, pageSize, pageNum));
        return "events";
    }

    @PostMapping("/update")
    public String updateUserById(@RequestParam long id,
                                 @RequestParam String title,
                                 @RequestParam String date,
                                 Model model) {

        Date parsedDate = dateParser.parseDate(date);

        Event event = new Event();
        event.setId(id);
        event.setTitle(title);
        event.setDate(parsedDate);

        model.addAttribute("user", eventService.updateEvent(event));

        return "redirect:/event/" + id;
    }

    @PostMapping("/delete")
    public String deleteUserById(@RequestParam long id) {
        eventService.deleteEvent(id);
        return "redirect:/";
    }
}

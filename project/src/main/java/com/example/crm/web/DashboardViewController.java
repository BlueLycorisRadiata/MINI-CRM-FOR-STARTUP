package com.example.crm.web;

import com.example.crm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Controller
public class DashboardViewController {

  private final UserService userService;

  public DashboardViewController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String home() {
    return "redirect:/dashboard";
  }

  @GetMapping("/dashboard")
  public String dashboard(
      @RequestParam(required = false) String search,
      @RequestParam(required = false) String tag,
      Model model
  ) {
    model.addAttribute("users", userService.list(search, tag));
    model.addAttribute("tags", userService.listAllTags());
    model.addAttribute("search", search == null ? "" : search);
    model.addAttribute("tag", tag == null ? "" : tag);

    // for rendering Instant nicely in Thymeleaf
    model.addAttribute("dtf",
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()));

    return "dashboard";
  }
}

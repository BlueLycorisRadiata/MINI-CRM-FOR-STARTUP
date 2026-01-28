package com.example.crm.web;

import com.example.crm.dto.CreateInteractionRequest;
import com.example.crm.service.InteractionService;
import com.example.crm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@RequestMapping("/customers")
public class CustomerViewController {

  private final UserService userService;
  private final InteractionService interactionService;

  public CustomerViewController(UserService userService, InteractionService interactionService) {
    this.userService = userService;
    this.interactionService = interactionService;
  }

  @GetMapping("/{id}")
  public String detail(@PathVariable UUID id, Model model) {
    model.addAttribute("user", userService.get(id));
    model.addAttribute("interactions", interactionService.listByUser(id));
    model.addAttribute("newInteraction", new CreateInteractionRequest("call", ""));

    model.addAttribute("dtf",
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()));

    return "customer-detail";
  }

  @PostMapping("/{id}/interactions")
  public String addInteraction(
      @PathVariable UUID id,
      @Valid @ModelAttribute("newInteraction") CreateInteractionRequest req,
      BindingResult binding,
      Model model
  ) {
    if (binding.hasErrors()) {
      // reload page with existing data + validation errors
      model.addAttribute("user", userService.get(id));
      model.addAttribute("interactions", interactionService.listByUser(id));
      model.addAttribute("dtf",
          DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()));
      return "customer-detail";
    }

    interactionService.create(id, req);
    return "redirect:/customers/" + id;
  }
}

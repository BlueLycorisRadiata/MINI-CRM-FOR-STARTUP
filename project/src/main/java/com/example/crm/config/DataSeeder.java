package com.example.crm.config;

import com.example.crm.entity.InteractionEntity;
import com.example.crm.entity.InteractionType;
import com.example.crm.entity.UserEntity;
import com.example.crm.repository.InteractionRepository;
import com.example.crm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataSeeder {

  @Bean
  CommandLineRunner seed(UserRepository userRepository, InteractionRepository interactionRepository) {
    return args -> {
      if (userRepository.count() > 0) return;

      UserEntity u1 = new UserEntity();
      u1.setName("Nguyen Van A");
      u1.setEmail("a@company.com");
      u1.setTags(Set.of("vip", "new"));
      u1 = userRepository.save(u1);

      UserEntity u2 = new UserEntity();
      u2.setName("Tran Thi B");
      u2.setEmail("b@company.com");
      u2.setTags(Set.of("new"));
      u2 = userRepository.save(u2);

      UserEntity u3 = new UserEntity();
      u3.setName("Le Van C");
      u3.setEmail("c@company.com");
      u3.setTags(Set.of("vip"));
      u3 = userRepository.save(u3);

      interactionRepository.save(makeInteraction(u1, InteractionType.CALL, "Gọi chào mừng khách hàng VIP."));
      interactionRepository.save(makeInteraction(u1, InteractionType.EMAIL, "Gửi email giới thiệu gói dịch vụ."));
      interactionRepository.save(makeInteraction(u2, InteractionType.CHAT, "Chat hỗ trợ đăng ký tài khoản."));
    };
  }

  private InteractionEntity makeInteraction(UserEntity user, InteractionType type, String note) {
    InteractionEntity it = new InteractionEntity();
    it.setUser(user);
    it.setType(type);
    it.setNote(note);
    return it;
  }
}


I. AI-ASSISTED DEVELOPMENT

1. Mình dùng ChatGPT và AntiGravity để hỗ trợ thiết kế nhanh cấu trúc dự án và viết một phần code mẫu.
2. AI đã giúp mình: Tích hợp Spring Security, thiết kế UI Thymeleaf và viết các logic API cơ bản 
như CRUD User, search theo name/email, filter tag, tạo & list Interaction theo User.
3. Theo mình, tính đến thời điểm hiện tại:

- Những gì mà AI làm rất tốt là: Tạo nhanh khung dự án và 
giúp mình cấu hình nhanh đoạn code, giúp tiết kiệm thời gian.

- Những gì mà AI chưa thể thay thế mình: công việc giữa 2 con người với nhau, không có tính con người 
(xử lý tình huống mơ hồ, không có kịch bản), phán đoán bối cảnh dự án, trong dự án thì là kiểm soát 
chất lượng, khả năng đưa ra quyết định theo ngữ cảnh.

II. THINKING & COMMUNICATION

1. Nếu phải bỏ đi những chức năng thì mình sẽ tạm bỏ chức năng search nâng cao
   (full-text, highlight, pagination), lọc tag, UI sẽ thiết kế đơn giản nhưng vẫn giữ
   API đầy đủ cho User & Interaction. Mình sẽ ưu tiên vào phát triển chức năng cốt lõi của dự án này

2. Nếu dự án này lên Production thực tế thì mình sẽ cải thiện
- Bảo mật (Security)
- Dashboard: search realtime, filter đa tag
- Timeline interaction rõ ràng hơn, group theo ngày, icon theo type
- Chuẩn hoá error response, logging, monitoring
- Bật HTTPS, audit log, rate limit API: giới hạn số lượng request nhằm chống quá tải hệ thống, DDoS
bình thường mỗi request của hệ thống có tính phí, vì vậy nó sẽ giúp mình tránh bill tăng đột biến

3. Theo ước lượng thực tế của mình thì:
Ai có thể giúp mình tiết kiệm ~40–70% thời gian so với làm thủ công hoàn toàn.

AI giúp mình rất nhiều ở:
- scaffold dự án, boilerplate code
- gợi ý cấu trúc chuẩn Spring Boot / Security / Thymeleaf

Phần con người vẫn phải làm:
- quyết định thiết kế theo ngữ cảnh bài toán
- chỉnh sửa, kết nối các phần

AI là accelerator, không phải người quyết định sản phẩm.

Techstach mình sử dụng:
SQL: MSSQL, ORM
Backend: Java(Springboot)
Frontend: Thymeleaf
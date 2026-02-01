
INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Nguyễn Văn A', N'nguyen.van.a@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'nguyen.van.a@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Trần Thị B', N'tran.thi.b@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'tran.thi.b@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Lê Văn C', N'le.van.c@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'le.van.c@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Phạm Thị D', N'pham.thi.d@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'pham.thi.d@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Hoàng Văn E', N'hoang.van.e@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'hoang.van.e@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Vũ Thị F', N'vu.thi.f@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'vu.thi.f@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Đỗ Văn G', N'do.van.g@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'do.van.g@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Bùi Thị H', N'bui.thi.h@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'bui.thi.h@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Đặng Văn I', N'dang.van.i@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'dang.van.i@company.com');

INSERT INTO users (id, name, email, created_at)
SELECT NEWID(), N'Ngô Thị J', N'ngo.thi.j@company.com', GETDATE()
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = N'ngo.thi.j@company.com');


------------------------------------------------------------
-- 2) USER TAGS
-- Tags: vip, new, active, premium, standard
------------------------------------------------------------

-- A: vip, premium
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'vip'
FROM users u
WHERE u.email = N'nguyen.van.a@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'vip');

INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'premium'
FROM users u
WHERE u.email = N'nguyen.van.a@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'premium');

-- B: new
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'new'
FROM users u
WHERE u.email = N'tran.thi.b@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'new');

-- C: vip, active
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'vip'
FROM users u
WHERE u.email = N'le.van.c@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'vip');

INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'active'
FROM users u
WHERE u.email = N'le.van.c@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'active');

-- D: standard
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'standard'
FROM users u
WHERE u.email = N'pham.thi.d@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'standard');

-- E: new, active
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'new'
FROM users u
WHERE u.email = N'hoang.van.e@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'new');

INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'active'
FROM users u
WHERE u.email = N'hoang.van.e@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'active');

-- F: premium
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'premium'
FROM users u
WHERE u.email = N'vu.thi.f@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'premium');

-- G: standard, active
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'standard'
FROM users u
WHERE u.email = N'do.van.g@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'standard');

INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'active'
FROM users u
WHERE u.email = N'do.van.g@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'active');

-- H: vip
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'vip'
FROM users u
WHERE u.email = N'bui.thi.h@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'vip');

-- I: new
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'new'
FROM users u
WHERE u.email = N'dang.van.i@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'new');

-- J: standard
INSERT INTO user_tags (user_id, tag)
SELECT u.id, N'standard'
FROM users u
WHERE u.email = N'ngo.thi.j@company.com'
  AND NOT EXISTS (SELECT 1 FROM user_tags ut WHERE ut.user_id = u.id AND ut.tag = N'standard');


------------------------------------------------------------
-- 3) INTERACTIONS (10)
------------------------------------------------------------

-- A: call, email
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'CALL', N'Gọi điện chào mừng khách hàng VIP mới', GETDATE()
FROM users u
WHERE u.email = N'nguyen.van.a@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Gọi điện chào mừng khách hàng VIP mới'
);

INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'EMAIL', N'Gửi email giới thiệu gói dịch vụ cao cấp', DATEADD(HOUR, -1, GETDATE())
FROM users u
WHERE u.email = N'nguyen.van.a@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Gửi email giới thiệu gói dịch vụ cao cấp'
);

-- B: chat
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'CHAT', N'Chat hỗ trợ đăng ký tài khoản mới', DATEADD(HOUR, -2, GETDATE())
FROM users u
WHERE u.email = N'tran.thi.b@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Chat hỗ trợ đăng ký tài khoản mới'
);

-- C: call
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'CALL', N'Gọi điện tư vấn về sản phẩm', DATEADD(HOUR, -3, GETDATE())
FROM users u
WHERE u.email = N'le.van.c@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Gọi điện tư vấn về sản phẩm'
);

-- D: email
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'EMAIL', N'Email thông báo khuyến mãi', DATEADD(HOUR, -4, GETDATE())
FROM users u
WHERE u.email = N'pham.thi.d@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Email thông báo khuyến mãi'
);

-- E: chat
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'CHAT', N'Chat hỗ trợ kỹ thuật', DATEADD(HOUR, -5, GETDATE())
FROM users u
WHERE u.email = N'hoang.van.e@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Chat hỗ trợ kỹ thuật'
);

-- F: call
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'CALL', N'Gọi điện xác nhận đơn hàng', DATEADD(HOUR, -6, GETDATE())
FROM users u
WHERE u.email = N'vu.thi.f@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Gọi điện xác nhận đơn hàng'
);

-- G: email
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'EMAIL', N'Email chăm sóc khách hàng định kỳ', DATEADD(HOUR, -7, GETDATE())
FROM users u
WHERE u.email = N'do.van.g@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Email chăm sóc khách hàng định kỳ'
);

-- H: chat
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'CHAT', N'Chat giải đáp thắc mắc về dịch vụ', DATEADD(HOUR, -8, GETDATE())
FROM users u
WHERE u.email = N'bui.thi.h@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Chat giải đáp thắc mắc về dịch vụ'
);

-- I: call
INSERT INTO interactions (id, user_id, type, note, created_at)
SELECT NEWID(), u.id, N'CALL', N'Gọi điện thu thập phản hồi', DATEADD(HOUR, -9, GETDATE())
FROM users u
WHERE u.email = N'dang.van.i@company.com'
  AND NOT EXISTS (
    SELECT 1 FROM interactions i
    WHERE i.user_id = u.id AND i.note = N'Gọi điện thu thập phản hồi'
);

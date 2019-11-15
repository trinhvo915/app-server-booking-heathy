INSERT INTO `role` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','USER');
INSERT INTO `role` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','ADMIN');
INSERT INTO `role` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','EXPERT');
INSERT INTO `degree` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','BÁC SỸ');
INSERT INTO `degree` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','THẠC SỸ');
INSERT INTO `degree` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','TIẾN SỸ');
INSERT INTO `degree` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','PHÓ GIÁO SƯ');
INSERT INTO `degree` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','GIÁO SƯ');


INSERT INTO `faculty` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','THẦN KINH');
INSERT INTO `faculty` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','TIÊU HÓA');
INSERT INTO `faculty` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','SẢN PHỤ KHOA');
INSERT INTO `faculty` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','DA LIỄU');

INSERT INTO `attachmentt_type` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','DAIDIEN');
INSERT INTO `attachmentt_type` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','POST');
INSERT INTO `attachmentt_type` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','CLINIC');

INSERT INTO `post_type` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','THÔNG TIN PHÒNG KHÁM');
INSERT INTO `post_type` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','TRANG THIẾT BỊ');
INSERT INTO `post_type` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','QUY TRÌNH KHÁM BỆNH');
INSERT INTO `post_type` (`id`,`created_at`,`update_at`,`is_active`,`name`) VALUES (UUID(),CURDATE(),CURDATE(),'1','NORMAR');
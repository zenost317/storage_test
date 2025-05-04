DROP database IF EXISTS `storagedb`;
CREATE database `storagedb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `storagedb`;

-- Bảng người dùng
CREATE TABLE `user` (
  `id` int not null auto_increment,
  `role` varchar(50) not null default 'Nhân viên',
  `name` varchar(200) not null,
  `number` int(11) not null default 0,
  `email` varchar(200) not null unique,
  `password` varchar(50) not null,
  `address` varchar(200) default null,
  `status` varchar(50) default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `user` (`role`, `name`, `number`, `email`, `password`, `address`, `status`) VALUES 
('Admin', 'Admin', '0123456789', 'admin@gmail.com', '123', 'Hà Nội', 'Active'),
('Nhân viên', 'test1', '0987654321', 'employee1@gmail.com', '456', 'Hồ Chí Minh', 'Active'),
('Nhân viên', 'test2', '0987654321', 'employee2@gmail.com', '789', 'Hải Phòng', 'Unactive'),
('Nhân viên', 'test3', '0987654321', 'employee3@gmail.com', '456', 'Đà Nẵng', 'Active'),
('Nhân viên', 'test4', '0987654321', 'employee4@gmail.com', '147', 'Ninh Bình', 'Unactive');

-- Bảng người cung cấp
CREATE TABLE `supplier` (
  `supplier_id` int not null auto_increment,
  `name` varchar(200) not null,
  `contact` varchar(50) default null,
  `email` varchar(200) default null,
  `address` varchar(200) default null,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB;

INSERT INTO `supplier` (`name`, `contact`, `email`, `address`) VALUES 
('Công ty ABC', '0123456789', 'abc@example.com', 'Hà Nội'),
('Công ty XYZ1', '0987654321', 'xyz1@example.com', 'Hồ Chí Minh'),
('Công ty XYZ2', '0987654321', 'xyz2@example.com', 'Hải Phòng'),
('Công ty XYZ3', '0987654321', 'xyz3@example.com', 'Đà Nẵng'),
('Công ty XYZ4', '0987654321', 'xyz4@example.com', 'Sài Gòn');

-- Bảng sản phẩm
CREATE TABLE `product` (
  `product_id` int not null auto_increment,
  `name` varchar(200) not null,
  `supplier_id` int not null,
  `price` decimal(15,4) not null,
  `quantity` int(11) not null default 0,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE RESTRICT
) ENGINE=InnoDB;

INSERT INTO `product` (`name`, `supplier_id`, `price`, `quantity`) VALUES 
('Sản phẩm A', 1, 100.0000, 192),
('Sản phẩm B', 2, 200.0000, 168);
 
 -- Bảng nhập hànguser
CREATE TABLE `import` (
  `import_id` int not null auto_increment,
  `order_code` varchar(50) not null, -- Mã phiếu nhập
  `supplier_id` int not null,
  `id` int not null,
  `import_date` date not null,
  `product_id` int not null,
  `quantity` int not null,
  `price` decimal(15,2) not null,
  PRIMARY KEY (`import_id`),
  FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE RESTRICT,
  FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- Bảng xuất hàng
CREATE TABLE `export` (
  `export_id` int not null auto_increment,
  `order_code` varchar(50) not null, -- Mã phiếu xuất
  `id` int not null,
  `export_date` date not null,
  `product_id` int not null,
  `quantity` int not null,
  `price` decimal(15,2) not null,
  PRIMARY KEY (`export_id`),
  FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE RESTRICT,
  FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT
) ENGINE=InnoDB;

-- Bảng tồn kho
CREATE TABLE `inventory` (
  `inventory_id` int not null auto_increment,
  `product_id` int not null,
  `quantity` int not null DEFAULT 0,
  PRIMARY KEY (`inventory_id`),
  FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB;



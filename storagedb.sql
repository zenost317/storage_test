DROP database IF EXISTS `storagedb`;
CREATE database `storagedb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `storagedb`;

-- Bảng người dùng
CREATE TABLE `user` (
  `id` int not null auto_increment,
  `role` varchar(50) not null default 'Nhân viên',
  `name` varchar(200) not null,
  `number` varchar(50) not null,
  `email` varchar(200) not null UNIQUE,
  `password` varchar(50) not null,
  `address` varchar(200) default null,
  `status` varchar(50) default null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Bảng người cung cấp
CREATE TABLE `supplier` (
  `supplier_id` int not null auto_increment,
  `name` varchar(200) not null,
  `contact` varchar(50) default null,
  `email` varchar(200) default null,
  `address` varchar(200) default null,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB;

-- Bảng sản phẩm
CREATE TABLE `product` (
  `product_id` int not null auto_increment,
  `name` varchar(200) not null,
  `supplier_id` int not null,
  `price` decimal(15,4) not null,
  `origin` varchar(200) default null,
  `description` text default null,
  PRIMARY KEY (`product_id`),
  FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE RESTRICT
) ENGINE=InnoDB;
 
 -- Bảng nhập hàng
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





USE MASTER
DROP DATABASE ApartmentDB
USE MASTER
GO
IF EXISTS (SELECT * FROM sysdatabases WHERE NAME = 'ApartmentDB')
	DROP DATABASE ApartmentDB
	GO
CREATE DATABASE ApartmentDB
GO
USE ApartmentDB
GO
CREATE TABLE Account( 
	username nvarchar(50) PRIMARY KEY,
	password nvarchar(50) not null,
	name  nvarchar(50) not null,
	SDT nvarchar(15) not null,
	Email nvarchar(150) not null,
	so_du BIGINT , 
	Seller int DEFAULT 0 not null, --1: la seller. 0 la nguoi dung bthuong
	role int not null DEFAULT 1,--0: admin, 1: người dùng--
)

GO
CREATE TABLE Tinh_ThanhPho( 
	id_thanhpho int PRIMARY KEY,
	ten_thanhpho nvarchar(50)
)
GO
CREATE TABLE Quan_Huyen( 
	id_quan int PRIMARY KEY,
	ten_quan nvarchar(50),
	id_thanhpho int, 
	CONSTRAINT FK_ThanhPho_idQsuan FOREIGN KEY(id_thanhpho) REFERENCES Tinh_ThanhPho(id_thanhpho)
)
GO
CREATE TABLE Phuong_Xa( 
	id_phuong int PRIMARY KEY,
	ten_phuong nvarchar(50),
	id_quan int
	CONSTRAINT FK_Phuongs_idquan FOREIGN KEY(id_quan) REFERENCES Quan_Huyen(id_quan)
)
GO
CREATE TABLE CanHo(
	id_canho INT IDENTITY(1,1) PRIMARY KEY,
	ten_canho nvarchar(50) ,
	so_phong int not null,
	dien_tich int not null,
	gia_tien int not null,
	mo_ta  nvarchar(max) not null,
	trang_thai_Dang int not null, --1: đã duyệt, 0 chưa duyệt
	trang_thai_Thue int not null, -- 1: đã thuê, 0: chưa được thuê
	hinh_anh nvarchar(50),
	id_phuong int not null,
	username nvarchar(50) not null,
	CONSTRAINT FK_CanHo_usernsame FOREIGN KEY(username) REFERENCES Account(username),
	CONSTRAINT FK_Canho_idphuonsg FOREIGN KEY(id_phuong) REFERENCES Phuong_Xa(id_phuong)
)
GO
CREATE TABLE HinhAnh(
	id_anh INT IDENTITY(1,1) PRIMARY KEY,
	ten_anh nvarchar(50),
	duong_dan nvarchar(100),
	id_canho int not null,
	CONSTRAINT FK_AnhCanHo_CanHo FOREIGN KEY(id_canho) REFERENCES CanHo(id_canho) ON DELETE CASCADE
)
GO
CREATE TABLE BaiDang(
	id_baidang  INT IDENTITY(1,1) PRIMARY KEY,
	tieu_de nvarchar(200) not null,
	ngay_dang date,
	trang_thai int not null, --1 đã đăng.  0 chờ duyệt
	id_canho int not null,
	CONSTRAINT FK_baidang_idcanhos FOREIGN KEY(id_canho) REFERENCES CanHo(id_canho)
)
GO
CREATE TABLE PhieuThanhToan( 
	id int PRIMARY KEY,	
	ngay_thanhtoan date,
	phuongthucthanhtoan nvarchar(50) ,
	tong_tien BIGINT,
	mo_ta nvarchar(50) ,
	trang_thai nvarchar(50) ,
	username nvarchar(50),
	CONSTRAINT FK_CanHo_usesrname FOREIGN KEY(username) REFERENCES Account(username)
)
GO
SET DATEFORMAT DMY
GO
INSERT INTO Account VALUES
(N'admin', N'21232f297a57a5a743894a0e4a801fc3', N'Quản trị viên', '0888888888', N'ndanhx@gmail.com',999999999,1, 3),
(N'duyanh', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0868888548', N'ndanhx@gmail.com',999999999,1, 0),
(N'seller', N'e10adc3949ba59abbe56e057f20f883e', N'Seller', '0123456789', N'ndanhx@gmail.com',0,1, 1),
(N'binhthuong', N'e10adc3949ba59abbe56e057f20f883e', N'Buyer', '0987654324', N'ndanhx@gmail.com',0,0, 1)
Go
insert into Tinh_ThanhPho values
(79,N'Thành phố Hồ Chí Minh'),
(86,N'Bình Thuận'),
(24,N'Hà Nội'),
(70,N'Tây Ninh')

								

Go
insert into Quan_Huyen values
(1,N'Quận 1',79),
(2,N'Quận 2',79),
(3,N'Quận 3',79),
(4,N'Quận 4',79),
(5,N'Quận 5',79),
(6,N'Quận 6',79),
(7,N'Quận 7',79),
(8,N'Quận 8',79),
(9,N'Quận 9',79),
(10,N'Quận 10',79),
(11,N'Quận 11',79),
(12,N'Quận 12',79),
(13,N'Quận Bình Thạnh',79),
(14,N'Quận Phú Nhuận',79),
(15,N'Thành phố Phan Thiết',86),
(16,N'Huyện Tuy Phong',86),
(17,N'Thị xã Lagi',86),
(18,N'Quận Hoàn Kiếm',24),
(19,N'Quận Tây Hồ',24),
(20,N'Quận Ba Đình',24),
(21,N'Quận Ba Đình',70),
(22,N'Quận Ba Đình',70)

Go
insert into Phuong_Xa values
(1,N'Phường 1',1),
(2,N'Phường 2',1),
(3,N'Phường 3',1),
(4,N'Phường 4',1),
(5,N'Phường 5',1),
(6,N'Phường Thảo Điền',1),
(7,N'Phường Bình Thuận',1),
(8,N'Phường 8',1),
(9,N'Phường Tân Phú',1),
(10,N'Phường 10',1),
(11,N'Phường Tân Định',1),
(12,N'Phường Thạnh Xuân',1),
(13,N'Phường 1',2),
(14,N'Phường 2',2),
(15,N'Phường 3',2),
(16,N'Phường 4',2),
(17,N'Phường 5',2),
(18,N'Phường Thảo Điền',2),
(19,N'Phường Bình Thuận',2),
(20,N'Phường 8',2),
(21,N'Phường Tân Phú',2),
(22,N'Phường 10',2),
(23,N'Phường Tân Định',2),
(24,N'Phường Thạnh Xuân',2),
(25,N'Phường 1',13),
(26,N'Phường 2',13),
(27,N'Phường 3',13),
(28,N'Phường 4',13),
(29,N'Phường 5',13),
(30,N'Phường 6',13),
(31,N'Phường 6',13),
(32,N'Phường 8',13),
(33,N'Phường 9',13),
(34,N'Phường 10',13),
(35,N'Phường 11',13),
(36,N'Phường 12',13)

Go
SET IDENTITY_INSERT CanHo ON;
insert into CanHo (id_canho,ten_canho, so_phong, dien_tich, gia_tien, mo_ta, trang_thai_Dang, trang_thai_Thue, hinh_anh, id_phuong, username) values
(1, N'Căn hộ Phường 1 Quận 1', 2, 100, 2500000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất', 1,0, null,1,N'duyanh' ),
(2, N'Căn hộ Phường 5 quận 1', 2, 100, 2500000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất', 1, 0, null,5,N'duyanh' ),
(3, N'Căn hộ Phường Thảo Điền Quận 1', 2, 100, 2500000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất', 1, 0, null,6,N'duyanh' ),
(4, N'Căn hộ Phường Bình Thuận Quận 1', 3, 100, 5000000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất', 1, 0, null,7,N'seller' ),
(5, N'Căn hộ Phường Thạnh Xuân Quận 1', 1, 20, 1200000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất, gần trường học, gần khu  chợ', 1, 0, null,12,N'seller' ),
(6, N'Căn hộ Phường Thảo Điền Quận 2', 1, 20, 1200000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất, gần trường học, gần khu  chợ', 1, 0, null,18,N'seller' ),
(7, N'Căn hộ phường Tân Định quận 2', 1, 20, 1200000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất, gần trường học, gần khu  chợ', 1, 0, null,23,N'seller' ),
(8, N'Căn hộ phường thạnh xuân quận 2', 1, 20, 1200000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất, gần trường học, gần khu  chợ', 1, 0, null,24,N'seller' ),
(9, N'Căn hộ phường 8 quận Bình Thạnh', 1, 20, 1200000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất, gần trường học, gần khu  chợ', 1, 0, null,32,N'seller' ),
(10, N'Căn hộ phường 9 quận Bình Thạnh', 1, 20, 1200000,N'Căn hộ tiện nghi, có đầy đủ tất cả nội thất, gần trường học, gần khu  chợ', 1, 0, null,33,N'seller' )
SET IDENTITY_INSERT CanHo OFF;
GO
SET IDENTITY_INSERT HinhAnh ON;
INSERT INTO HinhAnh (id_anh ,ten_anh,duong_dan,id_canho)  VALUES 
(1, N'Ảnh 1', N'images/img13.jpg', 1),
(2, N'Ảnh 2', N'images/img15.jpg', 1),
(3, N'Ảnh 1', N'images/img12.jpg', 2),
(4, N'Ảnh 2', N'images/img3.jpg', 2)
SET IDENTITY_INSERT HinhAnh OFF;
GO
INSERT INTO HinhAnh (ten_anh,duong_dan,id_canho)  VALUES 
(N'Ảnh 1', N'images/img18.png', 1),
(N'Ảnh 1', N'images/img13.jpg', 2),
(N'Ảnh 1', N'images/img15.jpg', 3),
(N'Ảnh 1', N'images/img2.jpg', 4),
(N'Ảnh 1', N'images/img3.jpg', 5),
(N'Ảnh 1', N'images/img4.jpg', 6),
(N'Ảnh 1', N'images/img10.jpg', 7),
(N'Ảnh 1', N'images/img11.jpg', 8),
(N'Ảnh 1', N'images/img12.jpg', 9)
GO

SET IDENTITY_INSERT BaiDang ON;
insert into BaiDang (id_baidang,tieu_de ,ngay_dang,trang_thai ,id_canho) values
(1,N'Cho Thuê Căn hộ Phường 1 Quận 1',GETDATE(),1,1),
(2,N'Cho Thuê Căn hộ Phường 5 Quận 1',GETDATE(),1,2),
(3,N'Cho Thuê Phường Thảo Điền Quận 1',GETDATE(),1,3),
(4,N'Cho Thuê Căn hộ Phường Bình Thuận Quận 1',GETDATE(),1,4),
(5,N'Cho Thuê căn hộ Phường Thạnh Xuân Quận 2',GETDATE(),1,5),
(6,N'Cho Thuê căn hộ Phường Thảo Điền Quận 2',GETDATE(),1,6),
(7,N'Cho Thuê căn hộ phường Tân Định quận 2',GETDATE(),1,7),
(8,N'Cho Thuê căn hộ phường thạnh xuân quận 2',GETDATE(),1,8),
(9,N'Cho Thuê căn hộ phường 8 quận Bình Thạnh',GETDATE(),1,9)
SET IDENTITY_INSERT BaiDang OFF;

GO
insert into BaiDang (tieu_de ,ngay_dang,trang_thai ,id_canho) values
(N'Cho Thuê Căn hộ Phường 1 Quận 1','2023-04-1',1,1),
(N'Cho Thuê Căn hộ Phường 1 Quận 1','2023-04-4',1,1)


GO
INSERT INTO Account VALUES
(N'113', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'1145', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'1167', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'1189', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'22', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'33', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'24', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'24563', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'24567', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'208', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'3', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'4', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'5', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'6', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'7', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'8', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'9', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1),
(N'10', N'e10adc3949ba59abbe56e057f20f883e', N'Duy Anh', '0987654324', N'ndanhx@gmail.com',0,0, 1)


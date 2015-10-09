
-- bảng Nhom_Hang đưa ra các nhóm hàng có trong siêu thị,gồm:
-- + MSNH : mã số nhóm hàng.
-- + TenNhomHang :Tên nhóm hàng 

create table if not exists `Nhom_Hang` (
	`MSNH` int not null,
	`TenNhomHang` varchar(50) not null,
	primary key(MSNH)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into `Nhom_Hang`(`MSNH`,`TenNhomHang`) values
(1,'Thực Phẩm Khô Ngọt - Mặn'),
(2,'Thực Phẩm Đông Lạnh - Thức Uống'),
(3,'Hóa Mỹ Phẩm'),
(4,'Thực Phẩm Tươi Sống - Thực Phẩm Chế Biến'),
(5,'Thời Trang'),
(6,'Vật Dụng Gia Đình'),
(7,'Điện Máy');



-- bảng Nha_Cung_Cap đưa ra thông tin của các nhà cung cấp sản phẩm, gồm
-- +MSNCC : mã số nhà cung cấp
-- +TenNhaCungCap : tên nhà cung cấp
-- + Dia_Chi : Địa chỉ 
-- +Dien_Thoai : điện thoại 
-- +Nguoi_Dai_Dien : người đại diện

create table if not exists `Nha_Cung_Cap` (
	`MSNCC` int not null,
	`TenNhaCungCap` varchar(50) not null,
	`Dia_Chi` varchar(50) not null,
	`Dien_Thoai` int(10) not null,
	`Nguoi_Dai_Dien` varchar(30) not null,
	 primary key(MSNCC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `Nha_Cung_Cap`(`MSNCC`,`TenNhaCungCap`,`Dia_Chi`,`Dien_THoai`,`Nguoi_Dai_Dien`) values
(1,'SONY','Số 12 Trần Đại Nghĩa - Hai Bà Trưng - Hà Nội',0435532186,'Nông Đức Mạnh'),
(2,'Vina milk','Hoàn Kiếm - Hà Nội',0426653985,'Nguyễn Tấn Dũng'),
(3,'vina soy','Đại La -Hà Nội',0453268565,'Nguyễn Hà Đông');

-- bảng Mat_Hang đưa ra thông tin các mặt hàng có bán, gồm 
-- +MSMH : mã số mặt hàng 
-- +MSNH : mã số nhóm hàng 
-- + Ten_San_Pham : tên sản phẩm 

create table if not exists `Mat_Hang`(
	`MSMH` int not null,
	`MSNH` int not null,
	`Ten_San_Pham` varchar(30) not null,
 	primary key(MSMH),
	foreign key(MSNH) references Nhom_Hang(MSNH)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `Mat_Hang`(`MSMH`,`MSNH`,`Ten_San_Pham`) values
(1,2,'sữa cô gái hà lan'),
(2,2,'sữa đậu nành fami'),
(3,7,'tivi sony 42 inch'),
(4,7,'máy nghe nhạc sony');


-- bảng Cung_Cap đưa ra thông tin về số lượng và giá cả hàng hóa được cung cấp, gồm 
-- +MSNCC : mã số nhà cung cấp
-- +MSMH : mã số mặt hàng 
-- +Gia_Nhap : giá nhập hàng 
-- +Gia_Ban : giá bán ra thị trường
-- +So_Luong_Nhap : số lượng hàng nhập 
-- +So_Luong_Ban : số lượng hàng đã bán ra thị trường 

create table if not exists `Cung_Cap` (
	`MSNCC` int not null,
	`MSMH` int not null,
	`Gia_Nhap` int not null,
	`Gia_Ban` int not null,
	`So_Luong_Nhap` int not null,
	`So_Luong_Ban` int not null,
	 foreign key(MSNCC) references Nha_Cung_Cap(MSNCC),
	 foreign key(MSMH) references Mat_Hang(MSMH)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `Cung_Cap`(`MSNCC`,`MSMH`,`Gia_Nhap`,`Gia_Ban`,`So_Luong_Nhap`,`So_Luong_Ban`) values 
(1,3,39000000,42000000,20,10),
(1,4,500000,600000,50,12),
(2,1,3000,3500,500,200),
(3,2,5000,5000,500,100);


-- bảng Su_Kien đưa ra thông tin về các sự kiện khuyễn mãi, giảm giá , gồm
-- +MSSK : mã số sự kiện 
-- +Bat_Dau  : thời gian bắt đầu sự kiên 
-- +Ket_Thuc : thời gian kết thúc sự kiên 
-- +TenSuKien : tên sự kiện
 
create table if not exists `Su_Kien`(
	`MSSK` int not null,
	`Bat_Dau` date not null,
	`Ket_Thuc` date not null,
	`TenSuKien` varchar(50) not null,
	primary key(MSSK)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `Su_Kien`(`MSSK`,`Bat_Dau`,`Ket_Thuc`,`TenSuKien`) values
(1,'2015/1/10','2015/1/30','Giảm giá sữa'),
(2,'2015/1/15','2015/1/22','Tuần lễ Sony');
 
-- bảng Ap_Dung đưa ra % giảm giá của từng mặt hàng trong từng đợt khuyễn mãi, gồm :
-- +MSSK : mã số sự kiên
-- +MSMH : mã số mặt hàng 
-- +Giam_Gia : % giảm giá 

create table if not exists `Ap_Dung`(
	`MSSK` int not null,
	`MSMH` int not null,
	`Giam_Gia` double  not null,
	 foreign key(MSSK) references Su_Kien(MSSK),
	 foreign key(MSMH) references Mat_Hang(MSMH)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `Ap_Dung` (`MSSK`,`MSMH`,`Giam_Gia`) values 
(1,1,50/100),
(1,2,10/100),
(2,3,20/100),
(2,4,30/100);


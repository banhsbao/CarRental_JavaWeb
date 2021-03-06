USE [Assignment03_HuynhChauBao]
GO
ALTER TABLE [dbo].[tblUsers] DROP CONSTRAINT [FK__tblUsers__roleID__276EDEB3]
GO
ALTER TABLE [dbo].[tblRate] DROP CONSTRAINT [FK__tblRate__idCar__4BAC3F29]
GO
ALTER TABLE [dbo].[tblRate] DROP CONSTRAINT [FK__tblRate__email__4AB81AF0]
GO
ALTER TABLE [dbo].[tblOrderDetail] DROP CONSTRAINT [FK__tblOrderD__order__114A936A]
GO
ALTER TABLE [dbo].[tblOrderDetail] DROP CONSTRAINT [FK__tblOrderD__carid__123EB7A3]
GO
ALTER TABLE [dbo].[tblOrder] DROP CONSTRAINT [FK__tblOrder__email__0C85DE4D]
GO
ALTER TABLE [dbo].[tblOrder] DROP CONSTRAINT [FK__tblOrder__discou__0E6E26BF]
GO
ALTER TABLE [dbo].[tblCar] DROP CONSTRAINT [FK__tblCar__cateId__2D27B809]
GO
ALTER TABLE [dbo].[tblUsers] DROP CONSTRAINT [DF__tblUsers__create__267ABA7A]
GO
ALTER TABLE [dbo].[tblOrder] DROP CONSTRAINT [DF__tblOrder__create__0D7A0286]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblUsers]') AND type in (N'U'))
DROP TABLE [dbo].[tblUsers]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblRoles]') AND type in (N'U'))
DROP TABLE [dbo].[tblRoles]
GO
/****** Object:  Table [dbo].[tblRate]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblRate]') AND type in (N'U'))
DROP TABLE [dbo].[tblRate]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblOrderDetail]') AND type in (N'U'))
DROP TABLE [dbo].[tblOrderDetail]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblOrder]') AND type in (N'U'))
DROP TABLE [dbo].[tblOrder]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblDiscount]') AND type in (N'U'))
DROP TABLE [dbo].[tblDiscount]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblCategory]') AND type in (N'U'))
DROP TABLE [dbo].[tblCategory]
GO
/****** Object:  Table [dbo].[tblCar]    Script Date: 3/7/2021 9:26:30 PM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[tblCar]') AND type in (N'U'))
DROP TABLE [dbo].[tblCar]
GO
/****** Object:  Table [dbo].[tblCar]    Script Date: 3/7/2021 9:26:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCar](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cateId] [int] NOT NULL,
	[name] [nvarchar](50) NULL,
	[color] [nvarchar](20) NULL,
	[img] [varchar](80) NULL,
	[year] [varchar](8) NULL,
	[price] [float] NULL,
	[quanity] [int] NULL,
	[createdDate] [date] NULL,
	[isActive] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 3/7/2021 9:26:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](40) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 3/7/2021 9:26:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[id] [varchar](10) NOT NULL,
	[name] [nvarchar](30) NULL,
	[value] [int] NULL,
	[expiredDate] [date] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 3/7/2021 9:26:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[id] [varchar](30) NOT NULL,
	[email] [nvarchar](80) NOT NULL,
	[totalPrice] [float] NULL,
	[createDate] [date] NOT NULL,
	[discountId] [varchar](10) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 3/7/2021 9:26:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[orderId] [varchar](30) NULL,
	[rentalDate] [date] NULL,
	[returnDate] [date] NULL,
	[carid] [int] NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
	[isActive] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRate]    Script Date: 3/7/2021 9:26:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRate](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [nvarchar](80) NOT NULL,
	[idCar] [int] NOT NULL,
	[rating] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 3/7/2021 9:26:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoles](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 3/7/2021 9:26:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUsers](
	[email] [nvarchar](80) NOT NULL,
	[password] [varchar](30) NULL,
	[phone] [varchar](13) NULL,
	[verifyCode] [varchar](20) NULL,
	[name] [nvarchar](30) NULL,
	[address] [nvarchar](80) NULL,
	[createDate] [date] NOT NULL,
	[roleID] [int] NOT NULL,
	[status] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblCar] ON 

INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (1, 1, N'Ferrari LaFerrari', N'Red', N'images/Lamborghini/Huracan.png', N'2020', 1420, 10, CAST(N'2021-01-29' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (2, 1, N'Ferrari F12 Berlinetta', N'Yellow', N'images/Lamborghini/580.png', N'2020', 1150, 10, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (3, 1, N'Ferrari 812 Superfast', N'Yellow', N'images/Lamborghini/Huracan.png', N'2021', 1000, 6, CAST(N'2021-02-01' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (4, 1, N'Ferrari 488 GTB', N'Red', N'images/Mercedes/GLB.png', N'2021', 1200, 20, CAST(N'2021-02-02' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (5, 1, N'Ferrari California T', N'Blue', N'images/Lexus/rc.png', N'2021', 1300, 10, CAST(N'2021-02-02' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (6, 1, N'Ferrari Portofino', N'White', N'images/Lexus/sc.png', N'2020', 900, 4, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (7, 1, N'Ferrari GTC4 Lusso', N'Black', N'images/Mercedes/GLB.png', N'2021', 1500, 10, CAST(N'2021-01-15' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (13, 1, N'Ferrari 488 Pista', N'Red', N'images/Mercedes/Benz.png', N'2021', 1600, 7, CAST(N'2021-03-01' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (14, 2, N'Lamborghini Aventador LP 700-4', N'Violet', N'images/Lamborghini/700.png', N'2020', 1500, 10, CAST(N'2021-02-28' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (15, 2, N'Lamborghini Urus', N'White', N'images/Lamborghini/urus.png', N'2021', 1700, 10, CAST(N'2021-02-27' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (16, 2, N'Lamborghini Huracan Performante', N'Orange', N'images/Lamborghini/Huracan.png', N'2021', 1300, 19, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (17, 2, N'Lamborghini Aventador S', N'Green', N'images/Lamborghini/Aventador.png', N'2020', 1800, 18, CAST(N'2021-01-27' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (18, 2, N'Lamborghini Huracan LP 610 - 4', N'Orange', N'images/Lamborghini/610.png', N'2019', 1400, 13, CAST(N'2021-01-28' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (19, 2, N'Lamborghini Huracan LP 580 – 2', N'Yellow', N'images/Lamborghini/580.png', N'2021', 1000, 10, CAST(N'2021-01-20' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (24, 3, N'Mercedes-Benz E-Class', N'Black', N'images/Mercedes/Benz.png', N'2020', 500, 20, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (25, 3, N'Mercedes-Benz S-Class Saloon', N'Black', N'images/Mercedes/Saloon.png', N'2021', 700, 30, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (26, 3, N'Mercedes-Benz GLB SUV', N'White', N'images/Mercedes/GLB.png', N'2021', 1000, 10, CAST(N'2021-01-01' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (27, 3, N'Mercedes-Benz GLS SUV', N'Black', N'images/Mercedes/GLS.png', N'2020', 1500, 8, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (28, 4, N'Lexus LS - Luxury Sedan', N'Black', N'images/Lexus/ls.png', N'2021', 2000, 10, CAST(N'2021-01-04' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (30, 4, N'Lexus ES – Executive Sedan', N'Black', N'images/Lexus/es.png', N'2020', 1500, 8, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (31, 4, N'Lexus NX - Luxury Crossover', N'Blue', N'images/Lexus/nx.png', N'2021', 1700, 10, CAST(N'2021-01-28' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (32, 4, N'Lexus RX - Crossover', N'Black', N'images/Lexus/rx.png', N'2021', 1300, 7, CAST(N'2021-01-19' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (33, 4, N'Lexus HS – Hybrid Sedan', N'Black', N'images/Lexus/hs.png', N'2020', 1300, 8, CAST(N'2021-01-19' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (34, 4, N'Lexus CT – Compact Touring', N'White', N'images/Lexus/ct.png', N'2021', 1500, 10, CAST(N'2021-02-02' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (35, 4, N'Lexus SC -Sport Coupe / Convertible', N'Blue', N'images/Lexus/sc.png', N'2021', 1800, 9, CAST(N'2021-03-01' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (36, 4, N'Lexus RC -Racing Coupe / Radiant Coupe', N'Radiant', N'images/Lexus/rc.png', N'2021', 1500, 10, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (37, 4, N'Lexus LFA', N'Yellow', N'images/Lexus/lfa.png', N'2020', 1900, 10, CAST(N'2021-01-30' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (39, 5, N'Tesla Roadster', N'While', N'images/Tesla/rd.png', N'2020', 1500, 9, CAST(N'2021-01-27' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (40, 5, N'Testa Model X', N'While', N'images/Tesla/x.png', N'2020', 1300, 5, CAST(N'2021-01-23' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (41, 5, N'Tesla Model S', N'While', N'images/Tesla/s.png', N'2020', 1300, 10, CAST(N'2020-01-23' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (42, 5, N'Tesla Model 3', N'Red', N'images/Tesla/3.png', N'2021', 1200, 3, CAST(N'2020-01-10' AS Date), 1)
INSERT [dbo].[tblCar] ([id], [cateId], [name], [color], [img], [year], [price], [quanity], [createdDate], [isActive]) VALUES (43, 5, N'Tesla CyberTruck', N'While', N'images/Tesla/Cyber.png', N'2020', 1000, 5, CAST(N'2020-01-30' AS Date), 1)
SET IDENTITY_INSERT [dbo].[tblCar] OFF
GO
SET IDENTITY_INSERT [dbo].[tblCategory] ON 

INSERT [dbo].[tblCategory] ([id], [name]) VALUES (1, N'Ferrari')
INSERT [dbo].[tblCategory] ([id], [name]) VALUES (2, N'Lamborghini')
INSERT [dbo].[tblCategory] ([id], [name]) VALUES (3, N'Mercedes')
INSERT [dbo].[tblCategory] ([id], [name]) VALUES (4, N'Lexus')
INSERT [dbo].[tblCategory] ([id], [name]) VALUES (5, N'Tesla')
SET IDENTITY_INSERT [dbo].[tblCategory] OFF
GO
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA10%OFF', N'Discount 10%', 10, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA11%OFF', N'Discount 11%', 11, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA12%OFF', N'Discount 12%', 12, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA13%OFF', N'Discount 13%', 13, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA14%OFF', N'Discount 14%', 14, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA15%OFF', N'Discount 15%', 15, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA16%OFF', N'Discount 16%', 16, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA19%OFF', N'Discount 19%', 19, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA20%OFF', N'Discount 20%', 20, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA30%OFF', N'Discount 30%', 30, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA50%OFF', N'Discount 50%', 50, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'MA70%OFF', N'Discount 70%', 70, CAST(N'2021-04-04' AS Date), 1)
INSERT [dbo].[tblDiscount] ([id], [name], [value], [expiredDate], [status]) VALUES (N'NOOFF', NULL, NULL, CAST(N'2022-04-04' AS Date), 1)
GO
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]36P07032021-201618', N'123@123', 4500, CAST(N'2021-03-07' AS Date), N'NOOFF', 0)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]3OF07032021-204214', N'123@123', 20700, CAST(N'2021-03-07' AS Date), N'NOOFF', 0)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]5SR07032021-202700', N'123@123', 1200, CAST(N'2021-03-07' AS Date), N'NOOFF', 0)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]7ZF07032021-201618', N'123@123', 4500, CAST(N'2021-03-07' AS Date), N'NOOFF', 0)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]DHY07032021-200650', N'123@123', 900, CAST(N'2021-03-07' AS Date), N'NOOFF', 0)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]GL607032021-201618', N'123@123', 4500, CAST(N'2021-03-07' AS Date), N'NOOFF', 1)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]I6Z07032021-193328', N'123@123', 5800, CAST(N'2021-03-07' AS Date), N'NOOFF', 0)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]JU807032021-204310', N'123@123', 2000, CAST(N'2021-03-07' AS Date), N'NOOFF', 1)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]LST07032021-202632', N'123@123', 1200, CAST(N'2021-03-07' AS Date), N'NOOFF', 1)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]OTL07032021-193328', N'123@123', 5800, CAST(N'2021-03-07' AS Date), N'NOOFF', 1)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]PKP07032021-163754', N'123@123', 13000, CAST(N'2021-03-07' AS Date), N'NOOFF', 1)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]QO707032021-193327', N'123@123', 5800, CAST(N'2021-03-07' AS Date), N'NOOFF', 1)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]U8U07032021-173835', N'123@123', 1800, CAST(N'2021-03-07' AS Date), N'MA30%OFF', 1)
INSERT [dbo].[tblOrder] ([id], [email], [totalPrice], [createDate], [discountId], [status]) VALUES (N'[MA]XZ407032021-193428', N'123@123', 1200, CAST(N'2021-03-07' AS Date), N'NOOFF', 1)
GO
SET IDENTITY_INSERT [dbo].[tblOrderDetail] ON 

INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1010, N'[MA]U8U07032021-173835', CAST(N'2021-03-13' AS Date), CAST(N'2021-03-18' AS Date), 28, 3, 6000, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1011, N'[MA]QO707032021-193327', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-18' AS Date), 18, 2, 5800, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1012, N'[MA]OTL07032021-193328', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-18' AS Date), 16, 1, 5800, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1013, N'[MA]I6Z07032021-193328', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-18' AS Date), 15, 1, 5800, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1014, N'[MA]XZ407032021-193428', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-18' AS Date), 42, 1, 1200, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1015, N'[MA]DHY07032021-200650', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 6, 1, 900, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1016, N'[MA]7ZF07032021-201618', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 28, 1, 4500, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1017, N'[MA]36P07032021-201618', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 43, 1, 4500, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1018, N'[MA]GL607032021-201618', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 7, 1, 4500, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1019, N'[MA]LST07032021-202632', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 42, 1, 1200, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1020, N'[MA]5SR07032021-202700', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 42, 1, 1200, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1021, N'[MA]3OF07032021-204214', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 39, 1, 20700, 1)
INSERT [dbo].[tblOrderDetail] ([id], [orderId], [rentalDate], [returnDate], [carid], [quantity], [price], [isActive]) VALUES (1022, N'[MA]JU807032021-204310', CAST(N'2021-03-08' AS Date), CAST(N'2021-03-10' AS Date), 28, 1, 2000, 1)
SET IDENTITY_INSERT [dbo].[tblOrderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[tblRate] ON 

INSERT [dbo].[tblRate] ([id], [email], [idCar], [rating]) VALUES (1018, N'123@123', 40, 10)
INSERT [dbo].[tblRate] ([id], [email], [idCar], [rating]) VALUES (1019, N'123@123', 40, 10)
INSERT [dbo].[tblRate] ([id], [email], [idCar], [rating]) VALUES (1020, N'123@123', 40, 10)
SET IDENTITY_INSERT [dbo].[tblRate] OFF
GO
SET IDENTITY_INSERT [dbo].[tblRoles] ON 

INSERT [dbo].[tblRoles] ([id], [name]) VALUES (1, N'admin')
INSERT [dbo].[tblRoles] ([id], [name]) VALUES (1001, N'member')
SET IDENTITY_INSERT [dbo].[tblRoles] OFF
GO
INSERT [dbo].[tblUsers] ([email], [password], [phone], [verifyCode], [name], [address], [createDate], [roleID], [status]) VALUES (N'123@123', N'123123', N'0356773024', N'123123', N'BanhsBao', N'192 YmoanEnuoi', CAST(N'2021-02-28' AS Date), 1001, N'Active')
INSERT [dbo].[tblUsers] ([email], [password], [phone], [verifyCode], [name], [address], [createDate], [roleID], [status]) VALUES (N'321@321', N'321321', N'1233123231', N'123123', N'Baohuynh', N'192 Loa Loa', CAST(N'2021-02-28' AS Date), 1, N'Active')
INSERT [dbo].[tblUsers] ([email], [password], [phone], [verifyCode], [name], [address], [createDate], [roleID], [status]) VALUES (N'huynhbaofaker@gmail.com', N'30113011', N'0356773024', N'[USER]NLCB1', N'Huá»³nh ChÃ¢u Báº£o', N'192 Ymoan', CAST(N'2021-03-07' AS Date), 1, N'Active')
GO
ALTER TABLE [dbo].[tblOrder] ADD  DEFAULT (getdate()) FOR [createDate]
GO
ALTER TABLE [dbo].[tblUsers] ADD  DEFAULT (getdate()) FOR [createDate]
GO
ALTER TABLE [dbo].[tblCar]  WITH CHECK ADD FOREIGN KEY([cateId])
REFERENCES [dbo].[tblCategory] ([id])
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD FOREIGN KEY([discountId])
REFERENCES [dbo].[tblDiscount] ([id])
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[tblUsers] ([email])
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD FOREIGN KEY([carid])
REFERENCES [dbo].[tblCar] ([id])
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD FOREIGN KEY([orderId])
REFERENCES [dbo].[tblOrder] ([id])
GO
ALTER TABLE [dbo].[tblRate]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[tblUsers] ([email])
GO
ALTER TABLE [dbo].[tblRate]  WITH CHECK ADD FOREIGN KEY([idCar])
REFERENCES [dbo].[tblCar] ([id])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([id])
GO

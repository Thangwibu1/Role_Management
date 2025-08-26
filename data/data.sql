CREATE DATABASE UserRoleDB;
GO
USE UserRoleDB;
GO

-- Tạo bảng Roles
CREATE TABLE Roles (
                       RoleID INT PRIMARY KEY IDENTITY(1,1),
                       RoleName NVARCHAR(50) NOT NULL UNIQUE
);
GO

-- Tạo bảng Users
CREATE TABLE Users (
                       UserID INT PRIMARY KEY IDENTITY(1,1),
                       Username NVARCHAR(50) NOT NULL UNIQUE,
                       PasswordHash NVARCHAR(255) NOT NULL, -- Sẽ lưu mật khẩu đã mã hóa
                       Email NVARCHAR(100) NOT NULL UNIQUE
);
GO

-- Tạo bảng trung gian User_Roles
CREATE TABLE User_Roles (
                            UserID INT,
                            RoleID INT,
                            PRIMARY KEY (UserID, RoleID), -- Khóa chính kết hợp
                            FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE,
                            FOREIGN KEY (RoleID) REFERENCES Roles(RoleID) ON DELETE CASCADE
);
GO

-- Chèn dữ liệu mẫu
-- 1. Chèn các Roles
INSERT INTO Roles (RoleName) VALUES ('admin'), ('user'), ('vip');
GO

-- 2. Chèn Users (mật khẩu mẫu là '123', bạn PHẢI mã hóa trong thực tế)
INSERT INTO Users (Username, PasswordHash, Email) VALUES
('admin', '123', 'admin@example.com'),
('testuser', '123', 'user@example.com');
GO

-- 3. Gán Role cho Users
-- Gán role 'admin' và 'user' cho user có UserID = 1
INSERT INTO User_Roles (UserID, RoleID) VALUES (1, 1), (1, 2);
-- Gán role 'user' cho user có UserID = 2
INSERT INTO User_Roles (UserID, RoleID) VALUES (2, 2);
GO
USE BookingSys
CREATE TABLE Customers
(
	CustomerID INT NOT NULL IDENTITY(100,1) PRIMARY KEY,
	Title Varchar(5) NOT NULL,
	FirstName Varchar(50) NOT NULL,
	LastName Varchar(50) NOT NULL,
	Phone Varchar(12) NOT NULL DEFAULT (000-000-0000),
	Email_Address Varchar(50) NOT NULL
)

GO

CREATE TABLE FoodMenu
(
	MenuID INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	MenuName Varchar(255) NOT NULL
)

GO

CREATE TABLE Decor
(
	DecorID INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	DecorDescription Varchar(255) NOT NULL
)

GO

CREATE TABLE Bookings
(
	BookingID INT NOT NULL IDENTITY(500,1) PRIMARY KEY,
	CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID),
	EventType Varchar(50) NOT NULL,
	[Date] Varchar(50) NOT NULL ,
	Venue Varchar(255) NOT NULL,
	NumOfPeople INT NOT NULL,
	TotalDue INT NOT NULL,
	Balance INT NOT NULL,
	Confirmation varchar(50) NOT NULL,
	Decorations INT NOT NULL FOREIGN KEY REFERENCES Decor(DecorID)
)

GO 

CREATE TABLE BookingFood
(
    BookingID INT NOT NULL FOREIGN KEY REFERENCES Bookings(BookingID),
    MenuID INT NOT NULL FOREIGN KEY REFERENCES FoodMenu(MenuID),
	Quantity INT NOT NULL
)

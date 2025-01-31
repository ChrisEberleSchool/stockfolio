-- Now drop the tables in reverse order of dependencies
DROP TABLE IF EXISTS "User";
DROP TABLE IF EXISTS Portfolio;
DROP TABLE IF EXISTS Investment;
DROP TABLE IF EXISTS Stock;

-- Drop tables if they exist (quoted to handle reserved words like "User")
DROP TABLE IF EXISTS "User";
DROP TABLE IF EXISTS "Portfolio";
DROP TABLE IF EXISTS "Investment";
DROP TABLE IF EXISTS "Stock";

-- Create User table
CREATE TABLE "User" (
    userID INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    dateCreated DATE,
    
    CONSTRAINT USER_PK PRIMARY KEY (userID),
    CONSTRAINT USER_EMAIL_UNIQUE UNIQUE (email)
);

-- Create Portfolio table
CREATE TABLE Portfolio (
    portfolioID INT NOT NULL,
    userID INT NOT NULL,
    portfolioName VARCHAR(50) NOT NULL,
    dateCreated DATE,
    
    CONSTRAINT PORTFOLIO_PK PRIMARY KEY (portfolioID),
    CONSTRAINT PORTFOLIO_USER_FK FOREIGN KEY (userID) REFERENCES "User"(userID)
);

-- Create Investment table
CREATE TABLE Investment (
    investmentID INT NOT NULL,
    portfolioID INT NOT NULL,
    userID INT NOT NULL,
    priceAtInvestment FLOAT NOT NULL,
    investmentDate DATE,
    
    CONSTRAINT INVESTMENT_PK PRIMARY KEY (investmentID),
    CONSTRAINT INVESTMENT_PORTFOLIO_FK FOREIGN KEY (portfolioID) REFERENCES Portfolio(portfolioID),
    CONSTRAINT INVESTMENT_USER_FK FOREIGN KEY (userID) REFERENCES "User"(userID)
);

-- Create Stock table
CREATE TABLE Stock (
    stockID INT NOT NULL,
    tickerSymbol VARCHAR(50) NOT NULL,
    companyName VARCHAR(50) NOT NULL,
    currentPrice FLOAT NOT NULL,
    
    CONSTRAINT STOCK_PK PRIMARY KEY (stockID)
);

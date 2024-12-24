CREATE TABLE users (
    User_ID INTEGER PRIMARY KEY, 
    Name VARCHAR(255), 
    Email VARCHAR(255) UNIQUE, 
    Password VARCHAR(255), 
    Bio TEXT,
    Experience VARCHAR(255), 
    Location VARCHAR(255),
    JobTitle VARCHAR(255)
);


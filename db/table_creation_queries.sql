CREATE TABLE users (
    User_ID INTEGER PRIMARY KEY, 
    Name VARCHAR(255), 
    Email VARCHAR(255) UNIQUE, 
    Password VARCHAR(255), 
    Bio TEXT
);

CREATE TABLE employers (
    User_ID INTEGER PRIMARY KEY, 
    Organization VARCHAR(255),
    FOREIGN KEY (User_ID) REFERENCES users(User_ID)
);

CREATE TABLE applicants (
    User_ID INTEGER PRIMARY KEY, 
    Experience VARCHAR(255), 
    Location VARCHAR(255),
    FOREIGN KEY (User_ID) REFERENCES users(User_ID)
);

CREATE TABLE job_positions (
    Job_ID INTEGER PRIMARY KEY, 
    Position VARCHAR(255), 
    Email_For_CV VARCHAR(255), 
    Location VARCHAR(255), 
    Description TEXT, 
    Experience_Required VARCHAR(255), 
    Emp_ID INTEGER,
    FOREIGN KEY (Emp_ID) REFERENCES employers(User_ID)
);

CREATE TABLE applications (
    Applicant_ID INTEGER, 
    Job_ID INTEGER,
    PRIMARY KEY (Applicant_ID, Job_ID),
    FOREIGN KEY (Applicant_ID) REFERENCES applicants(User_ID),
    FOREIGN KEY (Job_ID) REFERENCES job_positions(Job_ID)
);
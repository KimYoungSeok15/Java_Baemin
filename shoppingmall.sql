CREATE TABLE member (
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(100),
    pw VARCHAR(100),
    name VARCHAR(100),
    email VARCHAR(100),
    contact VARCHAR(100),
    CONSTRAINT MEMBER_PK PRIMARY KEY(id)
);

SELECT * FROM member;

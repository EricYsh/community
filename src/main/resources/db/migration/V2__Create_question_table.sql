create table QUESTION
(
    ID            INT auto_increment,
    TITLE         VARCHAR(100),
    DESCRIPTION   TEXT,
    GMT_CREATE    BIGINT,
    GMT_MODIFIED  BIGINT,
    CREATOR       INT,
    COMMENT_COUNT INT default 0,
    VIEW_COUNT    INT default 0,
    LIKE_COUNT    INT default 0,
    TAG           VARCHAR(256),
    JOBLINK       VARCHAR,
    COMPANY       VARCHAR,
    PLACE         VARCHAR,
    constraint QUESTION_PK
        primary key (ID)
);
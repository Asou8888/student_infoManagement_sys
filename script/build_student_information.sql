create table student_information (
    student_number CHAR(8) NOT NULL,
    name VARCHAR(40) NOT NULL,
    gender CHAR(6) NOT NULL,
    academy CHAR(40) NOT NULL,
    major VARCHAR(40) NOT NULL,
    native_place VARCHAR(40) NOT NULL,
    phone_number CHAR(15),
    email VARCHAR(40),
    PRIMARY KEY (student_number)
);
create index student_index
on student_information(student_number);

create table user_information (
    username CHAR(20) NOT NULL,
    password CHAR(20) NOT NULL,
    authentication BOOLEAN NOT NULL,
    PRIMARY KEY (username)
);

create table  user_student_link (
    username CHAR(20),
    student_number CHAR(8),
    foreign key(user_id) references user_information(user_id),
    foreign key(student_number) references student_information(student_number)
);

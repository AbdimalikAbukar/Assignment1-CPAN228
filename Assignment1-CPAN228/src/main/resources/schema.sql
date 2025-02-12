create table if not exists item (
    id int not null auto_increment,
    createdAt timestamp not null,
    name varchar(255) not null,
    description varchar(255) not null,
    price decimal not null,
    releaseDate date not null,
    brand varchar(255) not null,
    primary key (id)
);
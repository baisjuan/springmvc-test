
    create table diet (
        id bigint not null auto_increment,
        name varchar(255),
        score double precision not null,
        breakfast bigint,
        dinner bigint,
        lunch bigint,
        supper bigint,
        primary key (id)
    );

    create table plate (
        id bigint not null auto_increment,
        breakfast bit not null,
        calories integer not null,
        dinner bit not null,
        lunch bit not null,
        name varchar(255) not null unique,
        supper bit not null,
        primary key (id)
    );

    alter table diet 
        add index FK2F0BF42F739007 (lunch), 
        add constraint FK2F0BF42F739007 
        foreign key (lunch) 
        references plate (id);

    alter table diet 
        add index FK2F0BF4B83266B8 (breakfast), 
        add constraint FK2F0BF4B83266B8 
        foreign key (breakfast) 
        references plate (id);

    alter table diet 
        add index FK2F0BF4D9EAB94F (dinner), 
        add constraint FK2F0BF4D9EAB94F 
        foreign key (dinner) 
        references plate (id);

    alter table diet 
        add index FK2F0BF4F42D74EC (supper), 
        add constraint FK2F0BF4F42D74EC 
        foreign key (supper) 
        references plate (id);

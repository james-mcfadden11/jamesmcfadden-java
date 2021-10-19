
CREATE TABLE member
(
        member_number SERIAL PRIMARY KEY,
        last_name CHARACTER VARYING(50) NOT NULL,
        first_name CHARACTER VARYING(50) NOT NULL,
        email CHARACTER VARYING(50) NOT NULL UNIQUE,
        phone_number CHARACTER VARYING(11) NOT NULL,
        birthday DATE NOT NULL,
        email_reminders BOOLEAN DEFAULT FALSE
);


insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('Jim', 'Smith', 'jimsmith@yahoo.com', '123456789', '1/1/1988', true);

insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('Alex', 'Deise', 'ad@google.com', '654987', '5/5/1990', true);

insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('alex', 'bafile', 'abafile@hotmail.com', '987654321', '8/8/1990', false);

insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('chris', 'nicoletti', 'cn@yahoo.com', '654321', '9/9/1985', true);

insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('daniel', 'may', 'dmay@msn.com', '123789654', '7/7/1995', true);

insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('denny', 'walter', 'denny@denny.com', '456987', '4/4/1985', true);

insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('ellen', 'davis', 'ellen@TE.com', '321654987', '6/6/1990', true);

insert into member (last_name, first_name, email, phone_number, birthday, email_reminders)
values ('frank', 'gia', 'frank@gia.com', '123456', '2/2/1922', true);

--------------------------------------------------------------

CREATE TABLE event
(
        event_number SERIAL PRIMARY KEY,
        name CHARACTER VARYING(50) NOT NULL,
        description TEXT,
        start_date_time TIMESTAMP NOT NULL,
        duration_minutes int4 check(duration_minutes > '30'),
        group_in_charge INTEGER REFERENCES "group" (group_number),
        number_of_members INTEGER check(number_of_members >= '0')
);

insert into event (name, description, start_date_time, duration_minutes, group_in_charge, number_of_members)
values ('Java coding meetup', 'meeting up to write some java', '10/19/2021 5:00', 45, (select group_number from "group" where name = 'Java coders united'), 15);

insert into event (name, description, start_date_time, duration_minutes, group_in_charge, number_of_members)
values ('C# coding meetup', 'meeting up to write some C#', '10/27/2021 3:00', 100, (select group_number from "group" where name = 'C# enthusiasts'), 5);

insert into event (name, description, start_date_time, duration_minutes, group_in_charge, number_of_members)
values ('Python coding meetup', 'meeting up to write some java', '10/29/2021 5:00', 60, (select group_number from "group" where name = 'Python developers'), 25);

insert into event (name, description, start_date_time, duration_minutes, group_in_charge, number_of_members)
values ('Java coffee brewing meetup', 'meeting up to brew some java', '10/09/2022 1:00', 45, (select group_number from "group" where name = 'Java coders united'), 10);

-------------------------------------------------------------------

CREATE TABLE "group"
(
        group_number SERIAL PRIMARY KEY,
        name CHARACTER VARYING(100) NOT NULL UNIQUE,
        number_of_members INTEGER check(number_of_members >= '0')
);

insert into "group" (name, number_of_members)
values ('Java coders united', 29);

insert into "group" (name, number_of_members)
values ('C# enthusiasts', 3);

insert into "group" (name, number_of_members)
values ('Python developers', 20);

-------------------------------------------------------------

CREATE TABLE group_member
(
        group_number INTEGER references "group" (group_number),
        member_number INTEGER references member (member_number),
        
        CONSTRAINT pk_group_number_member_number PRIMARY KEY (group_number, member_number)
);

--email should be unique in member table
--group name should be unique

insert into group_member (group_number, member_number)
values ((select group_number from "group" where name = 'Java coders united'), (select member_number from member where email = 'ad@google.com'));

insert into group_member (group_number, member_number)
values ((select group_number from "group" where name = 'Java coders united'), (select member_number from member where email = 'cn@yahoo.com'));

insert into group_member (group_number, member_number)
values ((select group_number from "group" where name = 'C# enthusiasts'), (select member_number from member where email = 'ad@google.com'));

-------------------------------------------------------------

CREATE TABLE event_member
(
        event_number INTEGER references event (event_number),
        member_number INTEGER references member (member_number),
        
        CONSTRAINT pk_event_number_member_number PRIMARY KEY (event_number, member_number)
);

insert into event_member (event_number, member_number)
values ((select event_number from event where name = 'Java coding meetup'), (select member_number from member where email = 'ad@google.com'));

insert into event_member (event_number, member_number)
values ((select event_number from event where name = 'Java coding meetup'), (select member_number from member where email = 'cn@yahoo.com'));

insert into event_member (event_number, member_number)
values ((select event_number from event where name = 'Java coffee brewing meetup'), (select member_number from member where email = 'ad@google.com'));

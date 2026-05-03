create table if not exists users (
                                     id bigserial primary key,
                                     login varchar(255) not null unique,
                                     password_hash varchar(255) not null,
                                     telegram_username varchar(255) not null default '0',
                                     telegram_id bigint,
                                     role varchar(255) not null default 'User'
);
insert into users (login, password_hash, telegram_username, telegram_id, role)
values
    (
        'admin',
        '$2a$10$ez6fSW3s.TyUltml/jMs3e/XNMGSlNZgiDMnYoMA0gEae9B6j5AFG',
        'admin',
        null,
        'Admin'
    ),
    (
        'admin2',
        '$2a$10$pLa50ty4LBmvABuCmqYYEutVG4p/l7.SW4fgFb.Ms/mROYp1ifliC',
        'admin2',
        null,
        'Admin'
    )
on conflict (login) do nothing;
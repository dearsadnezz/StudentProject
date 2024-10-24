Для создания базы данных и внесения первичных данных для тестирования необходимо выполнить sql запрос.
Пользователь создается непосредственно при начале работы в системе.

CREATE TABLE IF NOT EXISTS public.status
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    computer_status text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "computerStatus_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.status
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.guest
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    surname text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    patronymic text COLLATE pg_catalog."default" NOT NULL,
    document text COLLATE pg_catalog."default" NOT NULL,
    address text COLLATE pg_catalog."default" NOT NULL,
    phone text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT visit_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.guest
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.computer
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    status_id bigint NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    plan integer NOT NULL,
    CONSTRAINT computer_pkey PRIMARY KEY (id),
    CONSTRAINT "computerStatusKey" FOREIGN KEY (status_id)
        REFERENCES public.status (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.computer
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.visit
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    guest_id bigint NOT NULL,
    computer_id bigint NOT NULL,
    date text COLLATE pg_catalog."default" NOT NULL,
    "time" integer NOT NULL,
    pay double precision NOT NULL,
    CONSTRAINT visit_pkey1 PRIMARY KEY (id),
    CONSTRAINT "computerKey" FOREIGN KEY (computer_id)
        REFERENCES public.computer (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE,
    CONSTRAINT "guestKey" FOREIGN KEY (guest_id)
        REFERENCES public.guest (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.visit
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    username text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;


INSERT INTO public.status(computer_status)
	VALUES ('Работает'), 
	('Не работает'),
	('На обслуживании'), 
	('На складе'),
	('Списан');

INSERT INTO public.guest(surname, name, patronymic, document, address, phone)
	VALUES ('Петрова', 'Валерия', 'Максимовна', '6017 657884', 'Наумова 10/2', '+79385713652'),
	('Тарасов', 'Валерий', 'Игоревич', '6020 148688', 'Книжная 161', '+79515432236'),
	('Мавренко', 'Даниил', 'Петрович', '6019 776832', 'Спортивная 102/6', '+79915678323'),
	('Павлова', 'Мария', 'Алексеевна', '6023 948754', 'Портовая 26', '+79884323448');

INSERT INTO public.computer(status_id, name, description, plan)
	VALUES (1, 'HyperPc Omen пк1 (Вип)', 'Мощный компьютер для стримов и тяжелых игр', 120),
	(1, 'HyperPc Omen пк2 (Вип)', 'Мощный компьютер для стримов и тяжелых игр', 120),
	(1, 'HyperPc Lumen пк1 (Базовый)', 'Компьютер базового плана из общего зала', 70),
	(2, 'HyperPc Omen пк3 (Вип)', 'Мощный компьютер для стримов и тяжелых игр', 120),
	(1, 'HyperPc Lumen пк2 (Базовый)', 'Компьютер базового плана из общего зала', 70),
	(1, 'HyperPc Lumen пк3 (Базовый)', 'Компьютер базового плана из общего зала', 70),
	(3, 'HyperPc Lumen пк1 (Базовый)', 'Компьютер базового плана из общего зала', 70),
	(3, 'Playstation 5 Pro (Бронь)', 'Комната на 5 человек, 2 геймпада, оплата за бронь комнаты', 500),
	(1, 'Xbox Series X (Бронь)', 'Комната на 4 человека, 2 геймпада, оплата за бронь комнаты', 400),
	(5, 'Playstation 4', 'Комната на 4 человека, 2 геймпада, почасовая оплата с человека', 80);

INSERT INTO public.visit(guest_id, computer_id, date, "time", pay)
	VALUES (1, 9, '2024-09-20', 3, 1200),
	(2, 1, '2024-09-20', 6, 720),
	(3, 2, '2024-09-20', 4, 480),
	(4, 5, '2024-09-20', 2, 140);

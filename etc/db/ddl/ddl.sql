-- public.task definition

-- Drop table

-- DROP TABLE public.task;

CREATE TABLE public.task (
	id serial NOT NULL,
	username varchar(50) NOT NULL,
	task varchar(100) NOT NULL,
	priority int2 NOT NULL,
	status bpchar(1) NOT NULL,
	description varchar(255) NULL,
	plan_date date NULL,
	done_date date NULL,
	CONSTRAINT task_pkey PRIMARY KEY (id)
);

-- public.task_comment definition

-- Drop table

-- DROP TABLE public.task_comment;

CREATE TABLE public.task_comment (
	id serial NOT NULL,
	task_id int4 NOT NULL,
	username varchar(50) NOT NULL,
	"comment" varchar(255) NULL,
	create_date timestamp NULL,
	CONSTRAINT task_comment_pkey PRIMARY KEY (id)
);

-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	username varchar(50) NOT NULL,
	"password" varchar(100) NOT NULL,
	enabledflg bpchar(1) NOT NULL,
	adminflg bpchar(1) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (username)
);
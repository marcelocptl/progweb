CREATE TABLE public.userdb (
  id INTEGER DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
  name VARCHAR(256),
  email VARCHAR(256),
  active BOOLEAN,
  CONSTRAINT user_pkey PRIMARY KEY(id)
) 
WITH (oids = false);
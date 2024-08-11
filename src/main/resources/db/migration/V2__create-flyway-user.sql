insert into 
  member (
    id, 
    avatar, 
    created_at, 
    email, 
    updated_at, 
    name, 
    password, 
    first_name, 
    last_name, 
    username
  )
values
  (
    gen_random_uuid(), 
    'avatar', 
    now(), 
    'flyway@email.com', 
    now(), 
    'flyway', 
    'AA!45aaa', 
    'flyway_user', 
    'flyway', 
    'flyway_user'
  );
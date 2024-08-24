insert into 
  member (
    id, 
    avatar, 
    created_at, 
    email, 
    first_name, 
    last_name, 
    updated_at, 
    username, 
    status, 
    "action"
  )
values
  (
    gen_random_uuid(),
    'https://gravatar.com/avatar/2ce6a05b6bdfbee03f37cf7aeb9ee327?s=400&d=robohash&r=x', 
    now(), 
    'flyway@email.com', 
    'flyway_first_name', 
    'flyway_user', 
    now(), 
    'flyway', 
    1, 
    0
  );
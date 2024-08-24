insert into 
    team (
      id, 
      created_at, 
      description, 
      updated_at, 
      name
    )
  values
    (
      gen_random_uuid(), 
      now(), 
      'flyway team', 
      now(), 
      'flyway'
    );
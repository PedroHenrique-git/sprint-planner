insert into 
      sprint (
        id, 
        created_at, 
        description, 
        updated_at, 
        name, 
        team_id
      )
    values
      (
        gen_random_uuid(), 
        now(), 
        'flyway sprint', 
        now(), 
        'flyway sprint', 
        (select id from team where name = 'flyway')
      );
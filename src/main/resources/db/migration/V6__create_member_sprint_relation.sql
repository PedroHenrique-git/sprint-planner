insert into 
          sprint_member (
            sprint_id, 
            member_id
          )
        values
          (
            (select id from sprint where name = 'flyway sprint'), 
            (select id from member where username = 'flyway')
          );
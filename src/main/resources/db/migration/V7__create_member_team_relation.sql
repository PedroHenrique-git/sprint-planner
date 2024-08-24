insert into 
            team_member (
              team_id, 
              member_id
            )
          values
            (
              (select id from team where name = 'flyway'), 
              (select id from member where username = 'flyway')
            );
## Users Table:

### Field	        Type	            Key	                                Constraints

    user_id	        int	                Primary	                            Autoincrement, Unique
    username	    varchar(50)		                                        Not null
    password	    varchar(255)		                                    Not null
    email	        varchar(255)		                                    Not null
    role_id	        int	                Foreign	References Roles(role_id)


## Roles Table

### Field	        Type	            Key	Constraints

role_id	int	Primary	Autoincrement, Unique
role_name	varchar(50)		Not null
permission	varchar(255)		Not null



Tasks table:
Field	Type	Key	Constraints
task_id	int	Primary	Autoincrement, Unique
task_name	varchar(255)		Not null
description	text		
assigned_to	int	Foreign	References Users(user_id)
created_by	int	Foreign	References Users(user_id)
status	varchar(50)		Not null
deadline	datetime		Not null
created_at	datetime		Not null
updated_at	datetime		Not null



Task Comments table:
Field	Type	Key	Constraints
comment_id	int	Primary	Autoincrement, Unique
task_id	int	Foreign	References Tasks(task_id)
user_id	int	Foreign	References Users(user_id)
comment_text	text		Not null
created_at	datetime		Not null




Attachments table:
Field	Type	Key	Constraints
attachment_id	int	Primary	Autoincrement, Unique
task_id	int	Foreign	References Tasks(task_id)
attachment_url	varchar(255)		Not null

Notifications table:
Field	Type	Key	Constraints
notification_id	int	Primary	Autoincrement, Unique
user_id	int	Foreign	References Users(user_id)
task_id	int	Foreign	References Tasks(task_id)
message	text		Not null
created_at	datetime		Not null


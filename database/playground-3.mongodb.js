use('taskmanagementdb');

db.getCollection('Comment').insertMany([
    { 'firstname': 'Neha', 'lastname': 'Raj', 'username': 'nraj', 'password': 'nraj@123', 'email': 'nraj@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Bharti', 'lastname': 'Prakash', 'username': 'bprak', 'password': 'bprak@123', 'email': 'bprak@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Silvi', 'lastname': 'Monga', 'username': 'smong', 'password': 'smong@123', 'email': 'smong@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Tony', 'lastname': 'Stark', 'username': 'tstar', 'password': 'tstar@123', 'email': 'tstar@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Peter', 'lastname': 'Parker', 'username': 'ppar', 'password': 'ppar@123', 'email': 'ppar@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Chandler', 'lastname': 'Bing', 'username': 'cbing', 'password': 'cbing@123',  'email': 'cbing@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Monical', 'lastname': 'Geller', 'username': 'mgel', 'password': 'mgel@123', 'email': 'mgel@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Rachel', 'lastname': 'Green', 'username': 'rgre', 'password': 'rgre@123', 'email': 'rgre@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Ross', 'lastname': 'Geller', 'username': 'rgel', 'password': 'rgel@123', 'email': 'rgel@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Pheobe', 'lastname': 'Buffay', 'username': 'pbuf', 'password': 'pbuf@123', 'email': 'pbuf@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Joey', 'lastname': 'Tribiiani', 'username': 'jtrib', 'password': 'jtrib@123', 'email': 'jtrib@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Arya', 'lastname': 'Stark', 'username': 'arstar', 'password': 'astar@123', 'email': 'astar@xyz.com', 'roles':['R1'] },
  ]);
  db.Comment.createIndex( { "username": 1 } , { unique: true , required : true} )

  db.getCollection('Notification').insertMany([
    { 'firstname': 'Neha', 'lastname': 'Raj', 'username': 'nraj', 'password': 'nraj@123', 'email': 'nraj@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Bharti', 'lastname': 'Prakash', 'username': 'bprak', 'password': 'bprak@123', 'email': 'bprak@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Silvi', 'lastname': 'Monga', 'username': 'smong', 'password': 'smong@123', 'email': 'smong@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Tony', 'lastname': 'Stark', 'username': 'tstar', 'password': 'tstar@123', 'email': 'tstar@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Peter', 'lastname': 'Parker', 'username': 'ppar', 'password': 'ppar@123', 'email': 'ppar@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Chandler', 'lastname': 'Bing', 'username': 'cbing', 'password': 'cbing@123',  'email': 'cbing@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Monical', 'lastname': 'Geller', 'username': 'mgel', 'password': 'mgel@123', 'email': 'mgel@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Rachel', 'lastname': 'Green', 'username': 'rgre', 'password': 'rgre@123', 'email': 'rgre@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Ross', 'lastname': 'Geller', 'username': 'rgel', 'password': 'rgel@123', 'email': 'rgel@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Pheobe', 'lastname': 'Buffay', 'username': 'pbuf', 'password': 'pbuf@123', 'email': 'pbuf@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Joey', 'lastname': 'Tribiiani', 'username': 'jtrib', 'password': 'jtrib@123', 'email': 'jtrib@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Arya', 'lastname': 'Stark', 'username': 'arstar', 'password': 'astar@123', 'email': 'astar@xyz.com', 'roles':['R1'] },
  ]);
  db.Notification.createIndex( { "username": 1 } , { unique: true , required : true} )

  db.getCollection('Attachment').insertMany([
    { 'firstname': 'Neha', 'lastname': 'Raj', 'username': 'nraj', 'password': 'nraj@123', 'email': 'nraj@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Bharti', 'lastname': 'Prakash', 'username': 'bprak', 'password': 'bprak@123', 'email': 'bprak@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Silvi', 'lastname': 'Monga', 'username': 'smong', 'password': 'smong@123', 'email': 'smong@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Tony', 'lastname': 'Stark', 'username': 'tstar', 'password': 'tstar@123', 'email': 'tstar@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Peter', 'lastname': 'Parker', 'username': 'ppar', 'password': 'ppar@123', 'email': 'ppar@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Chandler', 'lastname': 'Bing', 'username': 'cbing', 'password': 'cbing@123',  'email': 'cbing@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Monical', 'lastname': 'Geller', 'username': 'mgel', 'password': 'mgel@123', 'email': 'mgel@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Rachel', 'lastname': 'Green', 'username': 'rgre', 'password': 'rgre@123', 'email': 'rgre@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Ross', 'lastname': 'Geller', 'username': 'rgel', 'password': 'rgel@123', 'email': 'rgel@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Pheobe', 'lastname': 'Buffay', 'username': 'pbuf', 'password': 'pbuf@123', 'email': 'pbuf@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Joey', 'lastname': 'Tribiiani', 'username': 'jtrib', 'password': 'jtrib@123', 'email': 'jtrib@xyz.com', 'roles':['R1'] },
    { 'firstname': 'Arya', 'lastname': 'Stark', 'username': 'arstar', 'password': 'astar@123', 'email': 'astar@xyz.com', 'roles':['R1'] },
  ]);
  db.Attachment.createIndex( { "username": 1 } , { unique: true , required : true} )

  
  
  
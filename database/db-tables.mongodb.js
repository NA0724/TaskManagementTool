/* global use, db */
// MongoDB Playground
// To disable this template go to Settings | MongoDB | Use Default Template For Playground.
// Make sure you are connected to enable completions and to be able to run a playground.
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.
// The result of the last command run in a playground is shown on the results panel.
// By default the first 20 documents will be returned with a cursor.
// Use 'console.log()' to print to the debug output.
// For more documentation on playgrounds please refer to
// https://www.mongodb.com/docs/mongodb-vscode/playgrounds/

// Select the database to use.
use('taskmanagementdb');


// Insert a few documents into the sales collection.
db.getCollection('User').insertMany([
  { 
    'firstname': 'Neha', 
    'lastname': 'Raj', 
    'username': 'nraj', 
    'password': 'nraj@123', 
    'email': 'nraj@xyz.com', 
    'role':[
      { 
        'rid': 'R1', 
        'name': 'Administrator', 
        'permissions': [
          {
            'pid': 'P1',
            'name': 'Create User'
          }],
        }
    ]
  },
  { 
    'firstname': 'Bharti', 
    'lastname': 'Prakash', 
    'username': 'bprak', 
    'password': 'bprak@123', 
    'email': 'bprak@xyz.com',
    'role':[
      { 
        'rid': 'R1', 
        'name': 'Administrator', 
        'permissions': [
          {
            'pid': 'P1',
            'name': 'Create User'
          }],
        }
    ]
  },
  { 
    'firstname': 'Silvi', 
    'lastname': 'Monga', 
    'username': 'smong', 
    'password': 'smong@123', 
    'email': 'smong@xyz.com',
    'role':[
      { 
      'rid': 'R1', 
      'name': 'Administrator', 
      'permissions': [
        {
          'pid': 'P1',
          'name': 'Create User'
        }],
      }
    ]
  },
]);
db.User.createIndex( { "username": 1, "email": 1 } , { unique: true , required : true} )


// Insert a few documents into the sales collection.
db.getCollection('Role').insertMany([
  { 
    'rid': 'R1', 
    'name': 'Administrator', 
    'permissions': [
      {
        'pid': 'P1',
        'name': 'Create User'
      }],
    },
  ]);
db.Role.createIndex( { "rid": 1, "name": 1} , {unique: true, required : true} )


// Insert a few documents into the sales collection.
db.getCollection('Permission').insertMany([
  { 
    'pid': 'P1', 
    'name': 'Create User'
  },
  { 
    'pid': 'P2', 
    'name': 'Update User' 
  },
  { 
    'pid': 'P3', 
    'name': 'Delete User' 
  },
]);
db.Permission.createIndex( { "pid": 1, "name": 1 } , { unique: true, required : true} )

db.getCollection('Comment').insertMany([
  { 
    'commentId': 'C1', 
    'taskId': 'T1', 
    'body': 'test comment', 
    'createdAt': '', 
    'createdBy': 'nraj' 
  },
]);
db.Comment.createIndex( { "commentId": 1, "taskId": 1 } , { unique: true , required : true} )

db.getCollection('Notification').insertMany([
  { 
    'notificationId': 'N1', 
    'taskId': 'T1', 
    'body':'test notification', 
    'userId': 'nraj'
  },
]);
db.Notification.createIndex( { "notificationId": 1, "userId": 1, "taskId": 1} , { unique: true , required : true} )

db.getCollection('Attachment').insertMany([
  { 
    'attachmentId': 'A1', 
    'taskId': 'T1', 
    'createdBy': 'nraj'
  },
]);
db.Attachment.createIndex( { "attachmentId": 1, "taskId": 1 } , { unique: true , required : true} )


db.getCollection('Task').insertMany([

  {
    'tid': 'T1',
    'title': 'Finish project proposal',
    'description': 'Write up the final version of the project proposal document',
    'status': 'In progress',
    'priority': 'High',
    'createdBy': 'nraj',
    'assignedTo': 'pbuf',
    'assignedBy': 'smong',
    'assignedDate': '2023-05-13',
    'assignedTime': '09:00 AM',
    'dueDate': '2023-06-01',
    'dueTime': '05:00 PM',
    'taskType': 'Document',
    'taskCategory': 'Project Management',
    'comments' : [
      {
        'commentId': 'C1', 
        'taskId': 'T1', 
        'body': 'test comment', 
        'createdAt': '', 
        'createdBy': 'nraj'
      }
    ],
    'attachments' : [
      {
        'attachmentId': 'A1', 
         'taskId': 'T1', 
         'createdBy': 'nraj'
      }
    ]
},
]);

db.Task.createIndex( {"tid": 1} , { unique: true, required : true} )



// Run a find command to view items sold on April 4th, 2014.
//const salesOnApril4th = db.getCollection('sales').find({
 // date: { $gte: new Date('2014-04-04'), $lt: new Date('2014-04-05') }
//}).count();

// Print a message to the output window.
//onsole.log(`${salesOnApril4th} sales occurred in 2014.`);

// Here we run an aggregation and open a cursor to the results.
// Use '.toArray()' to exhaust the cursor to return the whole result set.
// You can use '.hasNext()/.next()' to iterate through the cursor page by page.
//db.getCollection('sales').aggregate([
  // Find all of the sales that occurred in 2014.
//  { $match: { date: { $gte: new Date('2014-01-01'), $lt: new Date('2015-01-01') } } },
  // Group the total sales for each product.
//  { $group: { _id: '$item', totalSaleAmount: { $sum: { $multiply: [ '$price', '$quantity' ] } } } }
//]);
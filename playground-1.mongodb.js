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
db.User.createIndex( { "username": 1 } , { unique: true , required : true} )


// Insert a few documents into the sales collection.
db.getCollection('Role').insertMany([
  { 'rid': 'R1', 'name': 'Administrator', 'permissions': ['P1','P2','P3', 'P4','P5','P6','P7', 'P8', 'P9', 'P10'] },
  { 'rid': 'R2', 'name': 'Manager', 'permissions': ['P4','P5','P6','P7', 'P8', 'P9', 'P10'] },
  { 'rid': 'R3', 'name': 'Team Lead', 'permissions': ['P4','P5','P6','P7', 'P8', 'P9', 'P10'] },
  { 'rid': 'R4', 'name': 'Team Member', 'permissions': ['P4','P5','P6','P7', 'P8', 'P9', 'P10'] },
]);
db.Role.createIndex( { "rid": 1} , {unique: true, required : true} )


// Insert a few documents into the sales collection.
db.getCollection('Permission').insertMany([
  { 'pid': 'P1', 'name': 'Create User' },
  { 'pid': 'P2', 'name': 'Update User' },
  { 'pid': 'P3', 'name': 'Delete User' },
  { 'pid': 'P4', 'name': 'Create Task' },
  { 'pid': 'P5', 'name': 'Update Task' },
  { 'pid': 'P6', 'name': 'Delete Task' },
  { 'pid': 'P7', 'name': 'Create Group' },
  { 'pid': 'P8', 'name': 'Update Group' },
  { 'pid': 'P9', 'name': 'Delete Group' },
  { 'pid': 'P10', 'name': 'Add User into Group' },
  { 'pid': 'P11', 'name': 'Delete User from Group' },
]);
db.Permission.createIndex( { "pid": 1 } , { unique: true, required : true} )


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

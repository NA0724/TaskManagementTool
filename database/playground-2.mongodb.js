use('taskmanagementdb');

db.getCollection('Comment').insertMany([
    { 'commentId': '', 'taskId': '', 'body': '', 'createdAt': '', 'createdBy': '' },
  ]);
  db.Comment.createIndex( { "commentId": 1 } , { unique: true , required : true} )

  db.getCollection('Notification').insertMany([
    { 'notificationId': '', 'taskId': '', 'body':'', 'userId': ''},
    
  ]);
  db.Notification.createIndex( { "notificationId": 1 } , { unique: true , required : true} )

  db.getCollection('Attachment').insertMany([
    { 'attachmentId': '', 'taskId': '', 'createdBy': ''},
  ]);
  db.Attachment.createIndex( { "attachmentId": 1 } , { unique: true , required : true} )

  
  
  
  
  
  
  db.getCollection('Task').insertMany([

    {
      'tid': 'T1',
      'title': 'Finish project proposal',
      'description': 'Write up the final version of the project proposal document',
      'status': 'In progress',
      'priority': 'High',
      'assignedTo': 'pbuf',
      'assignedBy': 'smong',
      'assignedDate': '2023-05-13',
      'assignedTime': '09:00 AM',
      'dueDate': '2023-06-01',
      'dueTime': '05:00 PM',
      'comments': 'Need to include updated budget information',
      'taskType': 'Document',
      'taskCategory': 'Project Management',
      'attachments': ['proposal.docx', 'budget.xlsx']
  },
  {
    'tid': 'T2',
    'title': 'Review and provide feedback on proposal',
    'description': 'Review the project proposal document and provide feedback for improvements',
    'status': 'Pending review',
    'priority': 'Medium',
    'assignedTo': 'nraj',
    'assignedBy': 'pbuf',
    'assignedDate': '2023-05-14',
    'assignedTime': '02:00 PM',
    'dueDate': '2023-05-20',
    'dueTime': '05:00 PM',
    'comments': 'Please provide feedback by end of day on Friday',
    'taskType': 'Document review',
    'taskCategory': 'Project Management',
    'attachments': ['proposal.docx']
  },
  {
    'tid': 'T3',
    'title': 'Create wireframes for new feature',
    'description': 'Design wireframes for the new feature and present to the team for feedback',
    'status': 'In progress',
    'priority': 'High',
    'assignedTo': 'mgel',
    'assignedBy': 'smong',
    'assignedDate': '2023-05-15',
    'assignedTime': '10:00 AM',
    'dueDate': '2023-05-30',
    'dueTime': '05:00 PM',
    'comments': 'Please coordinate with UX designer for input',
    'taskType': 'Design',
    'taskCategory': 'Product Development',
    'attachments': []
  },
  {
    'tid': 'T4',
    'title': 'Review and approve wireframes',
    'description': 'Review the wireframes for the new feature and approve for development',
    'status': 'Pending review',
    'priority': 'Medium',
    'assignedTo': 'pbuf',
    'assignedBy': 'bprak',
    'assignedDate': '2023-05-20',
    'assignedTime': '02:00 PM',
    'dueDate': '2023-05-25',
    'dueTime': '05:00 PM',
    'comments': 'Please provide feedback by end of day on Wednesday',
    'taskType': 'Design review',
    'taskCategory': 'Product Development',
    'attachments': ['wireframes.pdf']
  },
  {
    'tid': 'T5',
    'title': 'Develop new feature',
    'description': 'Develop the new feature based on approved wireframes',
    'status': 'In progress',
    'priority': 'High',
    'assignedTo': 'bprak',
    'assignedBy': 'pbuf',
    'assignedDate': '2023-05-25',
    'assignedTime': '09:00 AM',
    'dueDate': '2023-06-15',
    'dueTime': '05:00 PM',
    'comments': 'Please coordinate with QA for testing and bug fixes',
    'taskType': 'Development',
    'taskCategory': 'Product Development',
    'attachments': ['wireframes.pdf', 'requirements.docx']
  }
  ]);
  
  db.Task.createIndex( {"tid": 1} , { unique: true, required : true} )

  
  
  
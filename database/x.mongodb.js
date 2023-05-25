use('taskmanagementdb');

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
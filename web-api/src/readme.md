# API TASK

## HTTP Requests

| Action              | Verb   | Route                   | Response                                |
|---------------------|--------|-------------------------|-----------------------------------------|
| Get all tasks       | GET    | `/api/task`             | All tasks, ordered by date and priority |
| Get a single task   | GET    | `/api/task/{id}`        | The requested task                      |
| Get tasks by date   | GET    | `/api/task/date/{date}` | All tasks for the specified date, ordered by priority |
| Create a new task   | POST   | `/api/task/`            | The newly created task                  |
| Modify a task       | PUT    | `/api/task/{id}`        | The modified task                       |
| Delete a task       | DELETE | `/api/task/{id}`        | No content (task deleted)               |

---

## Data Structure

### For Sending Data (Request Body)
```json
{
  "title": "String",
  "description": "String",
  "priority": "String" ["HIGH", "MEDIUM", "LOW"], 
  "dueDate": "dd-MM-yyyy"
}
```
- **Priority**: Defaults to `MEDIUM` if not provided.  
- **Due Date**: Defaults to today's date if not provided.  
- For creating or modifying a task, you must send at least one parameter.

---

### For Receiving Data (Response Body)
```json
{
  "id": int,
  "title": "String",
  "description": "String",
  "priority": "String" ["HIGH", "MEDIUM", "LOW"],
  "dueDate": "dd-MM-yyyy"
}
```

---

## Notes

1. All tasks are ordered by **date**.  
2. For tasks on the same date, they are ordered by **priority**.  

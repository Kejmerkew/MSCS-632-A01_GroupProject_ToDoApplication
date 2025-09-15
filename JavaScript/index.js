import inquirer from "inquirer";
import { getUsers, addUser } from "./services/UserService.js";
import { getTasks, addTask, markTaskComplete, removeTask } from "./services/TaskService.js";

async function mainMenu() {
  const { action } = await inquirer.prompt({
    type: "list",
    name: "action",
    message: "Choose an action:",
    choices: [
      "List tasks",
      "Add task",
      "Complete task",
      "Remove task",
      "Add user",
      "List users",
      "Exit"
    ]
  });

  switch (action) {
    case "List tasks":
      console.table(await getTasks());
      break;

    case "Add task":
      const users = await getUsers();
      const answers = await inquirer.prompt([
        { type: "input", name: "title", message: "Task title:" },
        { type: "input", name: "category", message: "Task category:" },
        {
          type: "list",
          name: "assignedTo",
          message: "Assign to:",
          choices: users.map(u => ({ name: u.name, value: u.id }))
        }
      ]);
      await addTask(answers.title, answers.category, answers.assignedTo);
      console.log("✅ Task added.");
      break;

    case "Complete task":
      const tasks = await getTasks();
      const { completeId } = await inquirer.prompt({
        type: "list",
        name: "completeId",
        message: "Select a task to complete:",
        choices: tasks.map(t => ({ name: t.title, value: t.id }))
      });
      await markTaskComplete(completeId);
      console.log("✅ Task completed.");
      break;

    case "Remove task":
      const tasksToRemove = await getTasks();
      const { removeId } = await inquirer.prompt({
        type: "list",
        name: "removeId",
        message: "Select a task to remove:",
        choices: tasksToRemove.map(t => ({ name: t.title, value: t.id }))
      });
      await removeTask(removeId);
      console.log("🗑️ Task removed.");
      break;

    case "Add user":
      const { userName } = await inquirer.prompt({
        type: "input",
        name: "userName",
        message: "Enter user name:"
      });
      await addUser(userName);
      console.log("👤 User added.");
      break;

    case "List users":
      console.table(await getUsers());
      break;

    case "Exit":
      return false;
  }
  return true;
}

async function run() {
  let keepRunning = true;
  while (keepRunning) {
    keepRunning = await mainMenu();
  }
  console.log("👋 Goodbye!");
}

run();

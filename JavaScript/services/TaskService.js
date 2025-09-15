import { readDB, writeDB } from "../utils/db.js";

export async function getTasks() {
  const db = await readDB();
  return db.tasks;
}

export async function addTask(title, category, assignedTo) {
  const db = await readDB();
  const newTask = {
    id: Date.now(),
    title,
    category,
    assignedTo,
    status: "PENDING"
  };
  db.tasks.push(newTask);
  await writeDB(db);
  return newTask;
}

export async function markTaskComplete(taskId) {
  const db = await readDB();
  const task = db.tasks.find(t => t.id === taskId);
  if (task) {
    task.status = "COMPLETED";
    await writeDB(db);
    return task;
  }
  return null;
}

export async function removeTask(taskId) {
  const db = await readDB();
  db.tasks = db.tasks.filter(t => t.id !== taskId);
  await writeDB(db);
}

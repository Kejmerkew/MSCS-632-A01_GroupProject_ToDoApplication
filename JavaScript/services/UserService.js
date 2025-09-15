import { readDB, writeDB } from "../utils/db.js";

export async function getUsers() {
  const db = await readDB();
  return db.users;
}

export async function addUser(name) {
  const db = await readDB();
  const newUser = { id: Date.now(), name };
  db.users.push(newUser);
  await writeDB(db);
  return newUser;
}

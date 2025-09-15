import { readFile, writeFile } from "fs/promises";

const DB_FILE = "./data/db.json";

export async function readDB() {
  const data = await readFile(DB_FILE, "utf-8");
  return JSON.parse(data);
}

export async function writeDB(db) {
  await writeFile(DB_FILE, JSON.stringify(db, null, 2));
}

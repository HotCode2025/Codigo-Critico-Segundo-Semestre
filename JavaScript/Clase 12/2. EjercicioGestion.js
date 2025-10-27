function createTaskManager() {
    let tasks = []; // Este array es privado y solo accesible por las funciones de abajo
    let nextId = 1; // Un contador para asignar IDs únicos

    return {
        /**
         * Añade una nueva tarea a la lista.
         * @param {string} taskDescription - La descripción de la tarea.
         */
        addTask: function(taskDescription) {
            const newTask = {
                id: nextId,
                description: taskDescription,
                completed: false
            };
            tasks.push(newTask);
            console.log(`Tarea añadida: "${taskDescription}" (ID: ${nextId})`);
            nextId++; // Incrementamos el ID para la siguiente tarea
        },

        /**
         * Marca una tarea como completada usando su ID.
         * @param {number} taskId - El ID de la tarea a completar.
         */
        completeTask: function(taskId) {
            // Buscamos la tarea en el array
            const task = tasks.find(t => t.id === taskId);

            if (task) {
                task.completed = true;
                console.log(`Tarea completada: "${task.description}"`);
            } else {
                console.log(`Error: No se encontró la tarea con ID ${taskId}.`);
            }
        },

        /**
         * Muestra todas las tareas actuales en la consola.
         */
        listTasks: function() {
            console.log("\n--- Lista de Tareas ---");
            if (tasks.length === 0) {
                console.log("No hay tareas pendientes.");
            } else {
                tasks.forEach(task => {
                    const status = task.completed ? "[x]" : "[ ]"; // Marcador visual
                    console.log(`${status} (ID: ${task.id}) ${task.description}`);
                });
            }
            console.log("-----------------------\n");
        }
    };
}

// --- Uso ---

const myTasks = createTaskManager();

myTasks.addTask("Aprender JavaScript");
myTasks.addTask("Hacer ejercicio");
myTasks.addTask("Completar la carpeta en GitHub"); // Tarea actualizada

// Mostramos la lista inicial
myTasks.listTasks();

// Completamos una tarea
myTasks.completeTask(2); // Completamos "Hacer ejercicio"

// Mostramos la lista con una tarea completada
myTasks.listTasks();

// Completamos la nueva tarea de GitHub
myTasks.completeTask(3); 

// Mostramos la lista final
myTasks.listTasks();